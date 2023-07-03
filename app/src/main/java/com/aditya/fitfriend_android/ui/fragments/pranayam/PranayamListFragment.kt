package com.aditya.fitfriend_android.ui.fragments.pranayam

import android.opengl.Visibility
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
import com.aditya.fitfriend_android.adapters.PranayamsAdapter
import com.aditya.fitfriend_android.data.mappers.PranayamCacheMapper
import com.aditya.fitfriend_android.databinding.FragmentPranayamListBinding
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.utils.DataState
import com.aditya.fitfriend_android.viewmodels.PranayamStateEvent
import com.aditya.fitfriend_android.viewmodels.PranayamViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "PranayamListFragment"

@AndroidEntryPoint
class PranayamListFragment : Fragment() {

    private lateinit var binding: FragmentPranayamListBinding
    private lateinit var adapter: PranayamsAdapter
    private val viewModel: PranayamViewModel by viewModels()
    private var snackbar: Snackbar? = null
    private val args: PranayamListFragmentArgs by navArgs()
    private val pranayamCacheMapper = PranayamCacheMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!args.isCachedPranayams) {
            viewModel.setStateEvent(PranayamStateEvent.GetPranayamsEvent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentPranayamListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        binding.progressBarPranayams.visibility = View.GONE
        setUpAdapter()
        setUpRecyclerView()
        if (!args.isCachedPranayams) {
            subscribeToRemoteObservers()
        } else {
            viewModel.setStateEvent(PranayamStateEvent.GetCachedPranayamsEvent)
            subscribeToLocalObservers()
        }
    }

    private fun setUpAdapter() {
        val view = requireView()
        adapter = PranayamsAdapter(requireContext()) { Pranayam ->
            Log.i(TAG, "Navigating from list to detail fragment view")
            Log.i(TAG, "Navigating to Pranayam -> Name: ${Pranayam.name} ID: ${Pranayam.id}")
            val action =
                PranayamListFragmentDirections.actionPranayamListFragmentToPranayamFragment(
                    args.isCachedPranayams,
                    Pranayam.id
                )
            view.findNavController().navigate(action)
        }
    }

    private fun subscribeToRemoteObservers() {
        viewModel.pranayamsDataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<Pranayam>> -> {
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
                        viewModel.setStateEvent(PranayamStateEvent.GetPranayamsEvent)
                        return@Observer
                    }
                    val exp = "Unable to resolve host \"fitfriend.onrender.com\": No address associated with hostname"
                    if (datastate.ex.message.equals(exp)) {
                        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
                        binding.progressBarPranayams.visibility = View.GONE
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

    private fun subscribeToLocalObservers() {
        viewModel.cachedPranayamList.observe(viewLifecycleOwner) { cachedPranayams ->
            val pranayams: List<Pranayam> = pranayamCacheMapper.mapFromEntityList(cachedPranayams)
            adapter.setData(pranayams)
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