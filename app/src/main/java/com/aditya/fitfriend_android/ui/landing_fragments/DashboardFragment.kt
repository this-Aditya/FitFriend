package com.aditya.fitfriend_android.ui.landing_fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.broadcast_receivers.AlarmReceiver
import com.aditya.fitfriend_android.databinding.FragmentDashboardBinding
import com.aditya.fitfriend_android.services.ActivityRecognitionService
import com.aditya.fitfriend_android.time_picker.TimeTaker
import com.aditya.fitfriend_android.utils.PermissionHandler
import com.aditya.fitfriend_android.utils.YogaDialogue
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.Calendar

private const val TAG = "DashboardFragment"

/**
 * Landing fragment for application, after sign-up, if not signed up then sign-up fragment will be visible first.
 */
class DashboardFragment : Fragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var permissioner: PermissionHandler
    private val schedulingOption = mutableListOf<String>()
    private lateinit var schedulingTitle: String
    private lateinit var alarmPendingIntent: PendingIntent
    private lateinit var alarmManager: AlarmManager
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
        arrayOf("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    } else {
        arrayOf(Manifest.permission.ACTIVITY_RECOGNITION)
    }

    private val activityPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Manifest.permission.ACTIVITY_RECOGNITION }
    else { "com.google.android.gms.permission.ACTIVITY_RECOGNITION" }

    private val permissionsLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            handlePermissionsResult(permissions)
        }

    private fun handlePermissionsResult(permissions: Map<String, Boolean>) {
        for ((permission, isGranted) in permissions) {
            when (isGranted) {
                true -> {
                    Log.i(TAG, "Permission Granted: $permission")
                    startActivityRecognitionService()
                }
                false -> Log.i(TAG, "Permission denied: $permission")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(requireActivity(), AlarmReceiver::class.java)
        alarmPendingIntent = PendingIntent.getBroadcast(requireContext(), ALARM_PI_CODE, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
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

        sharedPreferences = requireContext().getSharedPreferences("fit_friend_prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        schedulingTitle = sharedPreferences.getString("schedulingTitle", "No Alarm Set") ?: "No Alarm Set"
        val option = sharedPreferences.getString("schedulingOption","Schedule Alarm") ?: "Schedule Alarm"
        updateZerothPositionData(option)

        if (!allPermissionsApproved()) {
            requestPermissionss()
        }
        startActivityRecognitionService()
        // Transition to AsanaListFragment
        binding.ivAsanaUp.setOnClickListener {
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToAsanasListFragment(true)
            YogaDialogue.showDialogueBox(
                "asanas",
                requireContext(),
                requireView(),
                action0,
                action1
            )
        }

        // Transition to PranayamListFragment
        binding.ivPranaUp.setOnClickListener {
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToPranayamListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToPranayamListFragment(true)
            YogaDialogue.showDialogueBox(
                "pranayams", requireContext(), requireView(), action0, action1
            )
        }

        // Transition to Meditation List Fragment
        binding.ivMedUp.setOnClickListener {
            val action0 =
                DashboardFragmentDirections.actionDashboardFragmentToMeditationListFragment(false)
            val action1 =
                DashboardFragmentDirections.actionDashboardFragmentToMeditationListFragment(true)
            YogaDialogue.showDialogueBox(
                "meditations", requireContext(), requireView(), action0, action1
            )
        }

        // Transition to Sleep fragment
        // Only transition takes place when activity recognition permission is approved
        binding.ivSleepUp.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), activityPermission) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Please approve the necessary permissions to track activity first.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            view.findNavController().navigate(R.id.action_dashboardFragment_to_sleepFragment)
        }

        // Scheduling info Transition
        binding.ivTimerUp.setOnClickListener {
            showTimePickingOptions(schedulingTitle)
        }
    }

    private fun startActivityRecognitionService() {
        Log.i(TAG, "startActivityRecognitionService: ")
        val intent = Intent(requireContext(), ActivityRecognitionService::class.java)
        ContextCompat.startForegroundService(requireContext(), intent)
    }

    private fun requestPermissionss() {
        Log.i(TAG, "requestActivityRecognitionPermission: ")
        val message =
            "For the proper functioning activity_recognition and notification permissions are necessary. Tap yes to grant permissions."

        permissioner.requestPermissions(
            requireActivity(), permissions, permissionsLauncher,
            message
        )
    }

    fun updateZerothPositionData(data: String) {
        if (schedulingOption.isEmpty()) {
            schedulingOption.add(data)
        } else {
            schedulingOption.clear()
            schedulingOption.add(data)
        }
    }

    private fun allPermissionsApproved(): Boolean {
        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun showTimePickingOptions(title: String) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title).setItems(schedulingOption.toTypedArray()) { _, _ ->
            if (schedulingOption[0] == "Schedule Alarm") {
                val timePicker = TimeTaker()
                timePicker.show(childFragmentManager, "time_picker")
            } else if (schedulingOption[0] == "Cancel Current Alarm") {
                cancelAlarm()
            }

        }.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val cl = Calendar.getInstance()
        cl.set(Calendar.HOUR_OF_DAY, hourOfDay)
        cl.set(Calendar.MINUTE, minute)
        cl.set(Calendar.SECOND, 0)
        setAlarm(cl)
    }

    private fun setAlarm(cl: Calendar) {
        val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(cl.time)
        schedulingTitle = "Alarm set for $time"
        editor.putString("schedulingTitle", schedulingTitle)
        updateZerothPositionData("Cancel Current Alarm")
        editor.putString("schedulingOption", schedulingOption[0])
        editor.apply()
        Log.d(TAG, "scheduling Options = $schedulingOption")
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cl.timeInMillis, AlarmManager.INTERVAL_DAY, alarmPendingIntent)
        Toast.makeText(requireContext(), "Scheduled Time: $time", Toast.LENGTH_SHORT).show()
    }

    private fun cancelAlarm() {
        alarmManager.cancel(alarmPendingIntent)
        schedulingTitle = "No Alarm Set"
        editor.putString("schedulingTitle", schedulingTitle)
        updateZerothPositionData("Schedule Alarm")
        editor.putString("schedulingOption", schedulingOption[0])
        editor.apply()
        Log.d(TAG, "scheduling Options = $schedulingOption")
        Toast.makeText(requireContext(), "Cancelled Schedule", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ALARM_PI_CODE = 674981
    }
}


