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

package com.aditya.fitfriend_android.ui.fragments.pranayam

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
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.mappers.PranayamCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentPranayamBinding
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.PranayamStateEvent
import com.aditya.fitfriend_android.viewmodels.PranayamViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PranayamFragment"

@AndroidEntryPoint
class PranayamFragment : Fragment() {

    private lateinit var binding: FragmentPranayamBinding
    private lateinit var pranayam: Pranayam
    private val viewModel: PranayamViewModel by viewModels()
    private val args: PranayamFragmentArgs by navArgs()
    private val pranayamCacheMapper = PranayamCacheMapper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPranayamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.pranayamId
        handleActionButton()
        subscribeToObservers()
        viewModel.setStateEvent(PranayamStateEvent.GetPranayamEvent(id))
        handleClicks()
        binding.fabYoga.visibility = View.GONE
        binding.btnVideoPranayamD.visibility = View.GONE
    }

    private fun handleActionButton() {
        if (args.isCachedPranayam) binding.fabYoga.setImageResource(R.drawable.delete_yoga)
        else binding.fabYoga.setImageResource(R.drawable.add_yoga)
    }

    private fun handleClicks() {

        binding.btnIntroExpandPranayamD.setOnClickListener {
            if (binding.tvIntroDetailsPranayamD.visibility == View.GONE) {
                binding.tvIntroDetailsPranayamD.visibility = View.VISIBLE
                binding.btnIntroExpandPranayamD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvIntroDetailsPranayamD.visibility = View.GONE
                binding.btnIntroExpandPranayamD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnDescrExpandPranayamD.setOnClickListener {
            if (binding.tvDescrDetailsPranayamD.visibility == View.GONE) {
                binding.tvDescrDetailsPranayamD.visibility = View.VISIBLE
                binding.btnDescrExpandPranayamD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvDescrDetailsPranayamD.visibility = View.GONE
                binding.btnDescrExpandPranayamD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnBenefitsExpandPranayamD.setOnClickListener {
            if (binding.tvBenefitsDetailsPranayamD.visibility == View.GONE) {
                binding.tvBenefitsDetailsPranayamD.visibility = View.VISIBLE
                binding.btnBenefitsExpandPranayamD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvBenefitsDetailsPranayamD.visibility = View.GONE
                binding.btnBenefitsExpandPranayamD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnPrecautionsExpandPranayamD.setOnClickListener {
            if (binding.tvPrecautionsDetailPranayamD.visibility == View.GONE) {
                binding.tvPrecautionsDetailPranayamD.visibility = View.VISIBLE
                binding.btnPrecautionsExpandPranayamD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvPrecautionsDetailPranayamD.visibility = View.GONE
                binding.btnPrecautionsExpandPranayamD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnVideoPranayamD.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pranayam.videoUrl))
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(pranayam.videoUrl)
            )
            try {
                startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                startActivity(webIntent)
            }
        }
        binding.fabYoga.setOnClickListener {
            val pranayam: PranayamCacheEntity = pranayamCacheMapper.mapToEntity(pranayam)
            if (!args.isCachedPranayam) {
                viewModel.setStateEvent(PranayamStateEvent.AddPranayamToCacheEvent(pranayam))
                Toast.makeText(
                    requireContext(),
                    "Successfully saved ${pranayam.name}.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.setStateEvent(PranayamStateEvent.DeleteCachedPranayam(pranayam))
                Toast.makeText(requireContext(), "Deleted ${pranayam.name}.", Toast.LENGTH_SHORT)
                    .show()
                val action =
                    PranayamFragmentDirections.actionPranayamFragmentToPranayamListFragment(true)
                requireView().findNavController().navigate(action)
            }
        }
    }

    private fun subscribeToObservers() {
        Log.i(TAG, "Subscribed to observers for Pranayam")
        viewModel.pranayamDataState.observe(viewLifecycleOwner) { datastate ->
            when (datastate) {
                is DataState.Loading -> {
                    Log.d(TAG, "Data is loading ")
                    showProgressBar(true)
                }

                is DataState.Error -> {
                    val exp = "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
                        binding.progressBarpranayam.visibility = View.GONE
                        return@observe
                    }
                    showProgressBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex}", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success -> {
                    showProgressBar(false)
                    pranayam = datastate.data
                    Log.i(TAG, "Pranayam received -> ${pranayam.id} ${pranayam.name}")
                    processData()
                    binding.fabYoga.visibility = View.VISIBLE
                    binding.btnVideoPranayamD.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun processData() {
        Picasso.get().load(pranayam.imageUrl).into(binding.ivPranayamD)
        binding.tvPranayamD.text = pranayam.name
        binding.tvIntroDetailsPranayamD.text = pranayam.introduction
        binding.tvDescrDetailsPranayamD.text = pranayam.directions
        binding.tvBenefitsDetailsPranayamD.text = pranayam.benefits
        binding.tvPrecautionsDetailPranayamD.text = pranayam.precautions


    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBarpranayam.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: PranayamFragment")
    }

}