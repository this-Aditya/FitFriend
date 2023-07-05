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

package com.aditya.fitfriend_android.ui.fragments.asana

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
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.data.mappers.AsanaCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentAsanaBinding
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.AsanaStateEvent
import com.aditya.fitfriend_android.viewmodels.AsanaViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "AsanaFragment"

@AndroidEntryPoint
class AsanaFragment : Fragment() {

    private lateinit var binding: FragmentAsanaBinding
    private lateinit var asana: Asana
    private val viewModel: AsanaViewModel by viewModels()
    private val args: AsanaFragmentArgs by navArgs()
    private val asanaCacheMapper = AsanaCacheMapper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAsanaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.asanaId
        handleActionButton()
        subscribeToObservers()
        viewModel.setStateEvent(AsanaStateEvent.GetAsanaEvent(id))
        handleClicks()
        binding.fabYoga.visibility = View.GONE
        binding.btnVideoAsanaD.visibility = View.GONE
    }

    private fun handleActionButton() {
        if (args.isCachedAsana) binding.fabYoga.setImageResource(R.drawable.delete_yoga)
        else binding.fabYoga.setImageResource(R.drawable.add_yoga)
    }

    private fun handleClicks() {

        binding.btnIntroExpandAsanaD.setOnClickListener {
            if (binding.tvIntroDetailsAsanaD.visibility == View.GONE) {
                binding.tvIntroDetailsAsanaD.visibility = View.VISIBLE
                binding.btnIntroExpandAsanaD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvIntroDetailsAsanaD.visibility = View.GONE
                binding.btnIntroExpandAsanaD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnDescrExpandAsanaD.setOnClickListener {
            if (binding.tvDescrDetailsAsanaD.visibility == View.GONE) {
                binding.tvDescrDetailsAsanaD.visibility = View.VISIBLE
                binding.btnDescrExpandAsanaD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvDescrDetailsAsanaD.visibility = View.GONE
                binding.btnDescrExpandAsanaD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnBenefitsExpandAsanaD.setOnClickListener {
            if (binding.tvBenefitsDetailsAsanaD.visibility == View.GONE) {
                binding.tvBenefitsDetailsAsanaD.visibility = View.VISIBLE
                binding.btnBenefitsExpandAsanaD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvBenefitsDetailsAsanaD.visibility = View.GONE
                binding.btnBenefitsExpandAsanaD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnPrecautionsExpandAsanaD.setOnClickListener {
            if (binding.tvPrecautionsDetailAsanaD.visibility == View.GONE) {
                binding.tvPrecautionsDetailAsanaD.visibility = View.VISIBLE
                binding.btnPrecautionsExpandAsanaD.setImageResource(R.drawable.arrow_up)
            } else {
                binding.tvPrecautionsDetailAsanaD.visibility = View.GONE
                binding.btnPrecautionsExpandAsanaD.setImageResource(R.drawable.arrow_down)
            }
        }
        binding.btnVideoAsanaD.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse(asana.videoUrl))
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(asana.videoUrl)
            )
            try {
                startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                startActivity(webIntent)
            }
        }
        binding.fabYoga.setOnClickListener {
            val asana: AsanaCacheEntity = asanaCacheMapper.mapToEntity(asana)
            if (!args.isCachedAsana) {
                viewModel.setStateEvent(AsanaStateEvent.AddAsanaToCacheEvent(asana))
                Toast.makeText(
                    requireContext(),
                    "Successfully saved ${asana.name}.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.setStateEvent(AsanaStateEvent.DeleteCachedAsana(asana))
                Toast.makeText(requireContext(), "Deleted ${asana.name}.", Toast.LENGTH_SHORT)
                    .show()
                val action = AsanaFragmentDirections.actionAsanaFragmentToAsanasListFragment(true)
                requireView().findNavController().navigate(action)
            }
        }
    }

    private fun subscribeToObservers() {
        Log.i(TAG, "Subscribed to observers for asana")
        viewModel.asanaDataState.observe(viewLifecycleOwner) { datastate ->
            when (datastate) {
                is DataState.Loading -> {
                    Log.d(TAG, "")
                    showProgressBar(true)
                }

                is DataState.Error -> {
                    val exp = "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
                        binding.progressBarAsana.visibility = View.GONE
                        return@observe
                    }
                    showProgressBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex}", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success -> {
                    showProgressBar(false)
                    asana = datastate.data
                    Log.i(TAG, "Asana received -> ${asana.id} ${asana.name}")
                    processData()
                    binding.fabYoga.visibility = View.VISIBLE
                    binding.btnVideoAsanaD.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun processData() {
        Picasso.get().load(asana.imageUrl).into(binding.ivAsanaD)
        binding.tvAsanaD.text = asana.name
        binding.tvIntroDetailsAsanaD.text = asana.introduction
        binding.tvDescrDetailsAsanaD.text = asana.directions
        binding.tvBenefitsDetailsAsanaD.text = asana.benefits
        binding.tvPrecautionsDetailAsanaD.text = asana.precautions


    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBarAsana.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: AsanaFragment")
    }

}