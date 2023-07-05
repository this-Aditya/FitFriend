package com.aditya.fitfriend_android.ui.fragments.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.adapters.ActivityAdapter
import com.aditya.fitfriend_android.databinding.FragmentActivityBinding
import com.aditya.fitfriend_android.viewmodels.ActivityStateEvent
import com.aditya.fitfriend_android.viewmodels.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
    private lateinit var adapter: ActivityAdapter
    private val viewModel: ActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStateEvent(ActivityStateEvent.GetActivityEvent)
        adapter = ActivityAdapter(requireContext())
        binding.rvActivities.adapter = adapter
        binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.activities.observe(viewLifecycleOwner) {
            adapter.setdata(it)
        }
    }


}