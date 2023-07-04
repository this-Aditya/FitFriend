package com.aditya.fitfriend_android.services

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.aditya.fitfriend_android.FitFriendApplication
import com.aditya.fitfriend_android.FitFriendApplication.Companion.CHANNEL_ID
import com.aditya.fitfriend_android.R
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest

private const val TAG = "ActivityRecognitionServ"

/**
 * Service for tracking sleep segment, classify and Activity recognition updates, this service will start
 * as soon as Sign-up for user succeeds.
 */
class ActivityRecognitionService : Service() {

    private val activityPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Manifest.permission.ACTIVITY_RECOGNITION }
    else { "com.google.android.gms.permission.ACTIVITY_RECOGNITION" }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        Log.i(TAG, "StartActivity Recogntition service 2")

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sleep and Activity tracking")
            .setContentText("Your sleep and activity data is being monitored continuously.")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(ACTIVITY_RECOGNITION_SERVICE_ID, notificationBuilder)

        registerForSleepUpdates()
        setAlarmForClearingClassifyEvents()
    }

    private fun setAlarmForClearingClassifyEvents() {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (ContextCompat.checkSelfPermission(this, activityPermission) != PackageManager.PERMISSION_GRANTED) {
            stopSelf()
        }

        return START_STICKY
    }

    private fun registerForSleepUpdates() {
        val permission = if (Build.VERSION.SDK_INT >= 29) Manifest.permission.ACTIVITY_RECOGNITION
        else "com.google.android.gms.permission.ACTIVITY_RECOGNITION"
        Log.i(TAG, "registerForSleepUpdates: .....")
        if (ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "ActivityRecognition permission approved. $permission")
            ActivityRecognition.getClient(this)
                .requestSleepSegmentUpdates(
                    FitFriendApplication.createSleepPendingIntent(
                        this
                    ), SleepSegmentRequest.getDefaultSleepSegmentRequest()
                )
                .addOnSuccessListener {
                    Log.d(TAG, "Successfully subscribed to sleep data.")
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Exception when subscribing to sleep data: ${exception.cause}")
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityRecognition.getClient(this)
            .removeSleepSegmentUpdates(FitFriendApplication.createSleepPendingIntent(this))
            .addOnSuccessListener {
                Log.i(TAG, "Unsubscribed from receiving sleep updates.")
            }
            .addOnFailureListener {
                Log.i(TAG, "Exception while unsubscribing from sleep updates: $it")
            }
    }

    companion object {
        private const val ACTIVITY_RECOGNITION_SERVICE_ID = 547655
    }
}
