package com.aditya.fitfriend_android.ui.landing_fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.aditya.fitfriend_android.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

private const val TAG = "DashboardFragment"

/**
 * Landing fragment for application, after sign-up, if not signed up then sign-up fragment will be visible first.
 */
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

        // Transition to AsanaListFragment
        binding.ivAsanaUp.setOnClickListener {
            showDialogueBox()
        }
    }

    private fun showDialogueBox() {
        val options = arrayOf("All Asanas", "Favourites")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Asanas")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                       val action = DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(false)
                        view?.findNavController()?.navigate(action)
                    }
                    1 -> {
                       val action = DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(true)
                        view?.findNavController()?.navigate(action)
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
}
