package com.aditya.fitfriend_android.ui.fragments.asana

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.fitfriend_android.adapters.AsanasAdapter
import com.aditya.fitfriend_android.data.mappers.AsanaCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentAsanaListBinding
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.AsanaStateEvent
import com.aditya.fitfriend_android.viewmodels.AsanaViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "AsanaListFragment"

@AndroidEntryPoint
class AsanaListFragment : Fragment() {

    private lateinit var binding: FragmentAsanaListBinding
    private lateinit var adapter: AsanasAdapter
    private val viewModel: AsanaViewModel by viewModels()
    private var snackbar: Snackbar? = null
    private val args: AsanaListFragmentArgs by navArgs()
    private val asanaCacheMapper = AsanaCacheMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!args.isCached) {
            viewModel.setStateEvent(AsanaStateEvent.GetAsanasEvent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentAsanaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        binding.progressBar.visibility = View.GONE
        setUpAdapter()
        setUpRecyclerView()
        if (!args.isCached) {
            subscribeToRemoteObservers()
        } else {
            viewModel.setStateEvent(AsanaStateEvent.GetCachedAsanasEvent)
            subscribeToLocalObservers()
        }
    }

    private fun setUpAdapter() {
        val view = requireView()
        adapter = AsanasAdapter(requireContext()) { asana ->
            Log.i(TAG, "Navigating from list to detail fragment view")
            Log.i(TAG, "Navigating to asana -> Name: ${asana.name} ID: ${asana.id}")
            val action =
                AsanaListFragmentDirections.actionAsanasListFragmentToAsanaFragment(args.isCached, asana.id)
            view.findNavController().navigate(action)
        }
    }

    private fun subscribeToRemoteObservers() {
        viewModel.asanasDataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<Asana>> -> {
                    showProgressBar(false)
                    showSnackBar(false)
                    adapter.setData(datastate.data)
                    Log.i(TAG, "Data received successfully")
                }

                is DataState.Loading -> {
                    showProgressBar(true)
                    lifecycleScope.launch {
                        delay(1500)
                        if (binding.progressBar.visibility == View.VISIBLE) showSnackBar(true)
                    }
                    Log.i(TAG, "Data loading")
                }

                is DataState.Error -> {
                    if (datastate.ex.message.equals("timeout")) {
                        viewModel.setStateEvent(AsanaStateEvent.GetAsanasEvent)
                        return@Observer
                    }
                    val exp = "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                        return@Observer
                    }
                    showProgressBar(false)
                    showSnackBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.w(TAG, "Exception while receiving data: ${datastate.ex.message}")
                }
            }
        })
    }

    private fun subscribeToLocalObservers() {
        viewModel.cachedAsanaList.observe(viewLifecycleOwner) { cachedAsanas ->
            val asanas: List<Asana> = asanaCacheMapper.mapFromEntityList(cachedAsanas)
            adapter.setData(asanas)
        }
    }

    private fun showSnackBar(show: Boolean) {
        if (show) {
            Log.d(TAG, "Snackbar loading...")
            snackbar = Snackbar.make(
                requireView(), "Apologies for the initial loading time. We're upgrading our servers, please wait.", Snackbar.LENGTH_INDEFINITE
            )
            snackbar?.show()
        } else {
            snackbar?.dismiss()
            Log.d(TAG, "Dismissed Snackbar")
        }
    }

    private fun showProgressBar(show: Boolean) {
        Log.d(TAG, "ProgressBar Visibility: $show")
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setUpRecyclerView() {
        binding.rvAsanas.adapter = adapter
        binding.rvAsanas.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        snackbar?.dismiss()
    }
}