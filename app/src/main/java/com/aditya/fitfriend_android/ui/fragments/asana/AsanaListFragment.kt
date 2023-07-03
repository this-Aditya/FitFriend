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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.adapters.AsanasAdapter
import com.aditya.fitfriend_android.databinding.FragmentAsanaListBinding
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.AsanaViewModel
import com.aditya.fitfriend_android.viewmodels.MainStateEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "AsanaListFragment"

@AndroidEntryPoint
class AsanaListFragment : Fragment() {

    private lateinit var binding: FragmentAsanaListBinding
    private lateinit var adapter: AsanasAdapter
//    private val asanas = mutableListOf<Asana>()
    private val viewModel: AsanaViewModel by viewModels()
    var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setStateEvent(MainStateEvent.GetAsanasEvent)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        subscribeToObservers()
    }

    private fun setUpAdapter() {
        val view = requireView()
        adapter = AsanasAdapter(requireContext()) { asana ->
            Log.i(TAG, "Navigating from list to detail fragment view")
            Log.i(TAG, "Navigating to asana -> Name: ${asana.name} ID: ${asana.id}")
            val action = AsanaListFragmentDirections.actionAsanasListFragmentToAsanaFragment(asana.id)
            view.findNavController().navigate(action)
        }
    }

    private fun subscribeToObservers() {
        viewModel.asanasDataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<Asana>> -> {
                    showProgressBar(false)
                    showSnackBar(false)
//                    asanas.clear()
//                    asanas.addAll(datastate.data)
                    adapter.setData(datastate.data)
                    Log.i(TAG, "Data received successfully")
//                    asanas.forEach {
//                        Log.d(TAG, "${it.name}  ${it.id}")
//                    }
                }

                is DataState.Loading -> {
                    showProgressBar(true)
                    showSnackBar(true)
                    Log.i(TAG, "Data loading")
            }

                is DataState.Error -> {
                    if (datastate.ex.message.equals("timeout")){
                        viewModel.setStateEvent(MainStateEvent.GetAsanasEvent)
                        return@Observer
                    }
                    showProgressBar(false)
                    showSnackBar(false)
                    Toast.makeText(requireContext(), "${datastate.ex.message}", Toast.LENGTH_SHORT)
                        .show()
                    Log.w(TAG, "Exception while receiving data: ${datastate.ex}")
                }
            }
        })
    }

    private fun showSnackBar(show: Boolean) {
        if (show) {
            Log.d(TAG, "Snackbar loading...")
                snackbar = Snackbar.make(
                    requireView(), "Please wait, it will take time while loading for first time. " +
                            "Apologies for this, " +
                            "soon it will be resolved.", Snackbar.LENGTH_INDEFINITE)
            snackbar?.show()
        }
        else {
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
}