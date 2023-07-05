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

package com.aditya.fitfriend_android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.aditya.fitfriend_android.broadcast_receivers.ActivityReceiver
import com.aditya.fitfriend_android.broadcast_receivers.SleepReceiver
import com.aditya.fitfriend_android.utils.toPendingIntentFlag
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "FitFriendApplication"

@HiltAndroidApp
class FitFriendApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =NotificationChannel(
                CHANNEL_ID, "fit_friend_notification", NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManger = getSystemService(NotificationManager::class.java)
            notificationManger.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "872594"

        fun createSleepPendingIntent(context: Context?): PendingIntent {
            val intent = Intent(context, SleepReceiver::class.java)
            Log.i(TAG, "Sleep pending intent created")
            return PendingIntent.getBroadcast(
                context,
                123,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT.toPendingIntentFlag(true)
            )
        }

        fun createActivityPendingIntent(context: Context?): PendingIntent {
            val intent = Intent(context, ActivityReceiver::class.java)
            Log.i(TAG, "Activity pending intent created.")
            return PendingIntent.getBroadcast(
                context,
                456,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }
    }
}