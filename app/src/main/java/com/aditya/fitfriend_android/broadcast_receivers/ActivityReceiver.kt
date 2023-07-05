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
import com.aditya.fitfriend_android.data.entities.ActivityEntity
import com.aditya.fitfriend_android.repository.YogaRepository
import com.google.android.gms.location.ActivityTransitionResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "ActivityReceiver"

@AndroidEntryPoint
class ActivityReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: YogaRepository
    private val scope: CoroutineScope = MainScope()

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.w(TAG, "onReceive: ActivityReceiver", )
        intent?.let {
            if (ActivityTransitionResult.hasResult(intent)) {
                val activities = mutableListOf<ActivityEntity>()
                val result = ActivityTransitionResult.extractResult(intent)
                result?.let {
                    Log.i(TAG, "onReceive: ")
                    result.transitionEvents.forEach {
                        val time = System.currentTimeMillis()
                        activities.add(ActivityEntity(0, time, it.activityType, it.transitionType))
                        Log.w(TAG, "Activity Received -> ${ActivityEntity.toActivityType(it.activityType)}, transition -> ${ActivityEntity.toTransitionType(it.transitionType)}", )
                    }
                    scope.launch {
                        repository.insertActivities(activities)
                        Log.i(TAG, "Inserted activity updates to database.")
                        activities.forEach {
                            Log.w(TAG, "Activity Received -> ${ActivityEntity.toActivityType(it.activityType)}, transition -> ${it.transition}}",)
                        }
                    }
                }
            }
        }
    }
}
