package com.aditya.fitfriend_android.ui.fragments.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.adapters.ActivityAdapter
import com.aditya.fitfriend_android.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
    private lateinit var adapter: ActivityAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}