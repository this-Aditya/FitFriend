/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.aditya.fitfriend_android.services

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
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
import com.aditya.fitfriend_android.broadcast_receivers.CleanupReceiver
import com.aditya.fitfriend_android.utils.ActivityTransitionUtil
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest
import java.util.Calendar

private const val TAG = "ActivityRecognitionServ"

/**
 * Service for tracking sleep segment, classify and Activity recognition updates, this service will start
 * as soon as Sign-up for user succeeds.
 */
class ActivityRecognitionService : Service() {

    private val activityPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Manifest.permission.ACTIVITY_RECOGNITION
    } else {
        "com.google.android.gms.permission.ACTIVITY_RECOGNITION"
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sleep and Activity tracking")
            .setContentText("Your sleep and activity data is being monitored continuously.")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(ACTIVITY_RECOGNITION_SERVICE_ID, notificationBuilder)

        registerForSleepUpdates()
        registerForActivityUpdates()
//        setAlarmForClearingClassifyEvents()
    }

    /**
     * Alarm manager that triggers every day at nearly 12:00 am to clear the cached data for
     * SleepClassifyEvents as, more of classify data is making spark chart messed up.
     * NOTE: Currently disabling this service, will resume it soon.
     */
    private fun setAlarmForClearingClassifyEvents() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, CleanupReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, CLEANUP_PI_CODE, intent, PendingIntent.FLAG_IMMUTABLE)
        val cl = Calendar.getInstance()
        cl.timeInMillis = System.currentTimeMillis()
        cl.set(Calendar.HOUR_OF_DAY, 0)
        cl.set(Calendar.MINUTE, 0)
        cl.set(Calendar.SECOND, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            cl.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (ContextCompat.checkSelfPermission(
                this,
                activityPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
        }

        return START_REDELIVER_INTENT
    }

    private fun registerForSleepUpdates() {

        Log.i(TAG, "registerForSleepUpdates: .....")
        if (ContextCompat.checkSelfPermission(this, activityPermission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "ActivityRecognition permission approved. $activityPermission")
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

    private fun registerForActivityUpdates() {
        if (ContextCompat.checkSelfPermission(this, activityPermission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "ActivityRecognition permission approved. $activityPermission")
            ActivityRecognition.getClient(this)
                .requestActivityTransitionUpdates(
                    ActivityTransitionUtil.getActivityTransitionRequests(),
                    FitFriendApplication.createActivityPendingIntent(this)
                )
                .addOnSuccessListener {
                    Log.i(TAG, "Successfully registered for activity transition updates")
                }
                .addOnFailureListener {
                    Log.i(TAG, "Exception while registering to activity Transition updates.")
                }
        }
    }

    private fun unregisterFromSleepUpdates() {
        if (ContextCompat.checkSelfPermission(this, activityPermission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityRecognition.getClient(this)
                .removeSleepSegmentUpdates(FitFriendApplication.createSleepPendingIntent(this))
                .addOnSuccessListener {
                    Log.i(TAG, "Unsubscribed from receiving sleep updates.")
                }
                .addOnFailureListener {
                    Log.i(TAG, "Exception while unsubscribing from sleep updates: $it")
                }
        }
    }


    private fun unregisterFromActivityRequests() {
        if (ContextCompat.checkSelfPermission(this, activityPermission) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityRecognition.getClient(this)
                .removeActivityTransitionUpdates(
                    FitFriendApplication.createActivityPendingIntent(this)
                )
                .addOnSuccessListener {
                    Log.i(TAG, "Unsubscribed from receiving activity updates.")
                }
                .addOnFailureListener {
                    Log.i(TAG, "Exception while unsubscribing from activity updates: $it")
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterFromSleepUpdates()
        unregisterFromActivityRequests()
    }

    companion object {
        private const val ACTIVITY_RECOGNITION_SERVICE_ID = 547655
        private const val CLEANUP_PI_CODE = 436981
    }
}
