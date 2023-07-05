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