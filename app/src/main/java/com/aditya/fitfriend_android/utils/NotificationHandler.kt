package com.aditya.fitfriend_android.utils

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.ui.MainActivity

class NotificationHandler {


    fun showNotification(context: Context, channelId: String, title: String, message: String) {
        createPendingIntent(context)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        val notificationManager = NotificationManagerCompat.from(context)
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    companion object {

        var pendingIntent: PendingIntent? = null

        fun createPendingIntent(context: Context) {
            if (pendingIntent != null) return
            val intent = Intent(context, MainActivity::class.java)
            pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_PI_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        private const val NOTIFICATION_PI_CODE = 917906
        const val NOTIFICATION_ID = 423894
    }
}