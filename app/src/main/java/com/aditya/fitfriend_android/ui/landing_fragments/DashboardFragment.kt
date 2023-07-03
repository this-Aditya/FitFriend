package com.aditya.fitfriend_android.ui.landing_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aditya.fitfriend_android.databinding.FragmentDashboardBinding
import com.aditya.fitfriend_android.utils.YogaDialogue
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
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(true)
            YogaDialogue.showDialogueBox("asanas", requireContext(), requireView(), action0, action1)
        }

        // Transition to PranayamListFragment
        binding.ivPranaUp.setOnClickListener {
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToPranayamListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToPranayamListFragment(true)
            YogaDialogue.showDialogueBox(
                "pranayams", requireContext(), requireView(), action0, action1)
        }
    // Transition to Meditation List Fragment
        binding.ivMedUp.setOnClickListener {
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToMeditationListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToMeditationListFragment(true)
            YogaDialogue.showDialogueBox(
                "meditations", requireContext(), requireView(), action0, action1)
        }
    }
}

