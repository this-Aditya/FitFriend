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

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.fitfriend_android.adapters.MeditationAdapter
import com.aditya.fitfriend_android.data.mappers.MeditationCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentMeditationListBinding
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.MeditationStateEvent
import com.aditya.fitfriend_android.viewmodels.MeditationViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MeditationListFragment"

@AndroidEntryPoint
class MeditationListFragment : Fragment() {

    private lateinit var binding: FragmentMeditationListBinding
    private lateinit var adapter: MeditationAdapter
    private val viewModel: MeditationViewModel by viewModels()
    private var snackbar: Snackbar? = null
    private val args: MeditationListFragmentArgs by navArgs()
    private val meditationCacheMapper = MeditationCacheMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!args.isCachedMeditations) {
            viewModel.setStateEvent(MeditationStateEvent.GetMeditationsEvent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentMeditationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        binding.progressBarPranayams.visibility = View.GONE
        setUpAdapter()
        setUpRecyclerView()
        if (!args.isCachedMeditations) {
            subscribeToRemoteObservers()
        } else {
            viewModel.setStateEvent(MeditationStateEvent.GetCachedMeditationsEvent)
            subscribeToLocalObservers()
        }
    }

    private fun setUpAdapter() {
        val view = requireView()
        adapter = MeditationAdapter(requireContext()) { meditation ->
            Log.i(TAG, "Navigating from list to detail fragment view")
            Log.i(TAG, "Navigating to meditation -> Name: ${meditation.name} ID: ${meditation.id}")
            val action =
                MeditationListFragmentDirections.actionMeditationListFragmentToMeditationfragment(
                    args.isCachedMeditations,
                    meditation.id
                )
            view.findNavController().navigate(action)
        }
    }

    private fun subscribeToRemoteObservers() {
        viewModel.meditationsDataState.observe(viewLifecycleOwner) { datastate ->
            when (datastate) {
                is DataState.Success<List<Meditation>> -> {
                    showProgressBar(false)
                    showSnackBar(false)
                    adapter.setData(datastate.data)
                    Log.i(TAG, "Data received successfully")
                }

                is DataState.Loading -> {
                    showProgressBar(true)
                    lifecycleScope.launch {
                        delay(1500)
                        if (binding.progressBarPranayams.visibility == View.VISIBLE) showSnackBar(
                            true
                        )
                    }
                    Log.i(TAG, "Data loading")
                }

                is DataState.Error -> {
                    if (datastate.ex.message.equals("timeout")) {
                        viewModel.setStateEvent(MeditationStateEvent.GetMeditationsEvent)
                        return@observe
                    }
                    val exp =
                        "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(
                            requireContext(),
                            "No internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarPranayams.visibility = View.GONE
                        return@observe
                    }
                    showProgressBar(false)
                    showSnackBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.w(TAG, "Exception while receiving data: ${datastate.ex}")
                }
            }
        }
    }

    private fun subscribeToLocalObservers() {
        viewModel.cachedMeditationList.observe(viewLifecycleOwner) { cachedMeditations ->
            val meditations: List<Meditation> =
                meditationCacheMapper.mapFromEntityList(cachedMeditations)
            adapter.setData(meditations)
        }
    }

    private fun showSnackBar(show: Boolean) {
        if (show) {
            Log.d(TAG, "Snackbar loading...")
            snackbar = Snackbar.make(
                requireView(),
                "Apologies for the initial loading time. We're upgrading our servers, please wait.",
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar?.show()
        } else {
            snackbar?.dismiss()
            Log.d(TAG, "Dismissed Snackbar")
        }
    }

    private fun showProgressBar(show: Boolean) {
        Log.d(TAG, "ProgressBar Visibility: $show")
        binding.progressBarPranayams.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setUpRecyclerView() {
        binding.rvPranayams.adapter = adapter
        binding.rvPranayams.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        snackbar?.dismiss()
    }
}