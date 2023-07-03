package com.aditya.fitfriend_android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

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

        fun createSleepPendingIntent() {

        }

        fun createActivityPendingIntent() {

        }
    }
}