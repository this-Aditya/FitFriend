package com.aditya.fitfriend_android.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.aditya.fitfriend_android.FitFriendApplication.Companion.CHANNEL_ID
import com.aditya.fitfriend_android.utils.NotificationHandler

private const val TAG = "AlarmReceiver"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "Alarm Received")
        val notification = NotificationHandler()
        context?.let {
            val title = "Yoga Time!!"
            val message = "Hello! It's time for your scheduled yoga practice. Wishing you all the best!"
            notification.showNotification(it, CHANNEL_ID, title, message)
        }
    }
}