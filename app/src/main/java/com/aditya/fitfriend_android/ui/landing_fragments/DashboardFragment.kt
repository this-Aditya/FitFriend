package com.aditya.fitfriend_android.ui.landing_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aditya.fitfriend_android.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

private const val TAG = "DashboardFragment"
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: DashboardFragment")
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: Dashboard Fragment")
        val name = auth.currentUser?.displayName
        val imageUri = auth.currentUser?.photoUrl
        binding.tvUserName.text = "Hi, $name"
        Picasso.get().load(imageUri).into(binding.userRealImg)
    }
}