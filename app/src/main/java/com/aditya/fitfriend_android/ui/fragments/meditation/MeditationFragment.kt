/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.aditya.fitfriend_android.ui.fragments.meditation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.mappers.MeditationCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentMeditationBinding
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.MeditationStateEvent
import com.aditya.fitfriend_android.viewmodels.MeditationViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MeditationFragment"

@AndroidEntryPoint
class MeditationFragment : Fragment() {

    private lateinit var binding: FragmentMeditationBinding
    private lateinit var meditation: Meditation
    private val viewModel: MeditationViewModel by viewModels()
    private val args: MeditationFragmentArgs by navArgs()
    private val meditationCacheMapper = MeditationCacheMapper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.meditationId
        handleActionButton()
        subscribeToObservers()
        viewModel.setStateEvent(MeditationStateEvent.GetMeditationEvent(id))
        handleClicks()
        binding.fabYoga.visibility = View.GONE
        binding.btnVideoMeditationD.visibility = View.GONE
    }

    private fun handleActionButton() {
        if (args.isCachedMeditation) binding.fabYoga.setImageResource(R.drawable.delete_yoga)
        else binding.fabYoga.setImageResource(R.drawable.add_yoga)
    }

    private fun handleClicks() {

        binding.btnIntroExpandMeditationD.setOnClickListener {
            if (binding.tvIntroDetailsMeditationD.visibility == View.GONE) {
                binding.tvIntroDetailsMeditationD.visibility = View.VISIBLE
                binding.btnIntroExpandMeditationD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvIntroDetailsMeditationD.visibility = View.GONE
                binding.btnIntroExpandMeditationD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnDescrExpandMeditationD.setOnClickListener {
            if (binding.tvDescrDetailsMeditationD.visibility == View.GONE) {
                binding.tvDescrDetailsMeditationD.visibility = View.VISIBLE
                binding.btnDescrExpandMeditationD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvDescrDetailsMeditationD.visibility = View.GONE
                binding.btnDescrExpandMeditationD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnBenefitsExpandMeditationD.setOnClickListener {
            if (binding.tvBenefitsDetailsMeditationD.visibility == View.GONE) {
                binding.tvBenefitsDetailsMeditationD.visibility = View.VISIBLE
                binding.btnBenefitsExpandMeditationD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvBenefitsDetailsMeditationD.visibility = View.GONE
                binding.btnBenefitsExpandMeditationD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnPrecautionsExpandMeditationD.setOnClickListener {
            if (binding.tvPrecautionsDetailMeditationD.visibility == View.GONE) {
                binding.tvPrecautionsDetailMeditationD.visibility = View.VISIBLE
                binding.btnPrecautionsExpandMeditationD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvPrecautionsDetailMeditationD.visibility = View.GONE
                binding.btnPrecautionsExpandMeditationD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnVideoMeditationD.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meditation.videoUrl))
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(meditation.videoUrl)
            )
            try {
                startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                startActivity(webIntent)
            }
        }
        binding.fabYoga.setOnClickListener {
            val meditation: MeditationCacheEntity = meditationCacheMapper.mapToEntity(meditation)
            if (!args.isCachedMeditation) {
                viewModel.setStateEvent(MeditationStateEvent.AddMeditationToCacheEvent(meditation))
                Toast.makeText(
                    requireContext(),
                    "Successfully saved ${meditation.name}.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.setStateEvent(MeditationStateEvent.DeleteCachedMeditation(meditation))
                Toast.makeText(requireContext(), "Deleted ${meditation.name}.", Toast.LENGTH_SHORT)
                    .show()
                val action =
                    MeditationFragmentDirections.actionMeditationFragmentToMeditationListFragment(
                        true
                    )
                requireView().findNavController().navigate(action)
            }
        }
    }

    private fun subscribeToObservers() {
        Log.i(TAG, "Subscribed to observers for Meditation")
        viewModel.meditationDataState.observe(viewLifecycleOwner) { datastate ->
            when (datastate) {
                is DataState.Loading -> {
                    Log.d(TAG, "Data is loading ")
                    showProgressBar(true)
                }

                is DataState.Error -> {
                    val exp =
                        "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(
                            requireContext(),
                            "No internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarmeditation.visibility = View.GONE
                        return@observe
                    }
                    showProgressBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex}", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success -> {
                    showProgressBar(false)
                    meditation = datastate.data
                    Log.i(TAG, "Meditation received -> ${meditation.id} ${meditation.name}")
                    processData()
                    binding.fabYoga.visibility = View.VISIBLE
                    binding.btnVideoMeditationD.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun processData() {
        Picasso.get().load(meditation.imageUrl).into(binding.ivMeditationD)
        binding.tvMeditationD.text = meditation.name
        binding.tvIntroDetailsMeditationD.text = meditation.introduction
        binding.tvDescrDetailsMeditationD.text = meditation.instructions
        binding.tvBenefitsDetailsMeditationD.text = meditation.benefits
        binding.tvPrecautionsDetailMeditationD.text = meditation.precautions


    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBarmeditation.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MeditationFragment")
    }

}