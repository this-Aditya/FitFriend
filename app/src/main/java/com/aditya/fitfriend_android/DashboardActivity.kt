package com.aditya.fitfriend_android

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditya.fitfriend_android.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        auth = FirebaseAuth.getInstance()
        val name = auth.currentUser?.displayName
        val imageUri = auth.currentUser?.photoUrl
        binding.tvUserName.text = "Hi! $name"
        Picasso.get().load(imageUri).into(binding.userRealImg)
    }
}