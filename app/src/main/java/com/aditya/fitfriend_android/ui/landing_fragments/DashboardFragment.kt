package com.aditya.fitfriend_android.ui.landing_fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aditya.fitfriend_android.databinding.FragmentDashboardBinding
import com.aditya.fitfriend_android.utils.PermissionHandler
import com.aditya.fitfriend_android.utils.YogaDialogue
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

private const val TAG = "DashboardFragment"

/**
 * Landing fragment for application, after sign-up, if not signed up then sign-up fragment will be visible first.
 */
class DashboardFragment : Fragment() {

    private val permissionsLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            handlePermissionsResult(permissions)
        }

    private fun handlePermissionsResult(permissions: Map<String, Boolean>) {
        for ((permission, isGranted) in permissions) {
            when (isGranted) {
                true -> Log.i(TAG, "Permission Granted: $permission")
                false -> Log.i(TAG, "Permission denied: $permission")
            }
        }
    }

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var permissioner: PermissionHandler

    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    }
    else if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        arrayOf("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    }
    else {
        arrayOf(Manifest.permission.ACTIVITY_RECOGNITION)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView: DashboardFragment")
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        permissioner = PermissionHandler()
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

        if (!allPermissionsApproved()){
            requestPermissionss()
        }
    }

    private fun requestPermissionss() {
        Log.i(TAG, "requestActivityRecognitionPermission: ")
        val message =
            "For the sleep Tracking these permissions are necessary, tap Yes to grant permissions."

        permissioner.requestPermissions(
            requireActivity(), permissions, permissionsLauncher,
            message
        )
    }

    private fun allPermissionsApproved(): Boolean {
        return permissions.any { permission ->
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
        }
    }

}

