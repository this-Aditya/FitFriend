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
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.repository.YogaRepository
import com.google.android.gms.location.SleepClassifyEvent
import com.google.android.gms.location.SleepSegmentEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SleepReceiver"

@AndroidEntryPoint
class SleepReceiver : BroadcastReceiver() {
    init {
        Log.d(TAG, "Init Block: Sleep Receiver")
    }

    private val scope: CoroutineScope = MainScope()
    @Inject
    lateinit var repository: YogaRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive: Sleep Receiver")

        intent?.let {
            if (SleepSegmentEvent.hasEvents(intent)) {
                Log.d(TAG, "SleepSegmentEvent data received")
                val sleepSegmentEvents: List<SleepSegmentEvent> =
                    SleepSegmentEvent.extractEvents(intent)
                updateSleepSegmentData(sleepSegmentEvents)
            }
            if (SleepClassifyEvent.hasEvents(intent)) {
                Log.d(TAG, "SleepClassifyEvent data received")
                val sleepClassifyEvents: List<SleepClassifyEvent> =
                    SleepClassifyEvent.extractEvents(intent)
                updateSleepClassifyEvents(sleepClassifyEvents)
            }
        }
    }

    private fun updateSleepClassifyEvents(sleepClassifyEvents: List<SleepClassifyEvent>) {
        scope.launch {
            val events = sleepClassifyEvents.map { SleepClassifyEntity.from(it) }
            repository.insertClassifyEvents(events)
            Log.i(TAG, "Inserted sleep classify data to database.")
            Log.d(TAG, "Classify Events: $events")
        }
    }

    private fun updateSleepSegmentData(sleepSegmentEvents: List<SleepSegmentEvent>) {
        scope.launch {
            val events = sleepSegmentEvents.map { SleepSegmentEntity.from(it) }
            repository.insertSegmentEvents(events)
            Log.i(TAG, "Inserted sleep segment data to database")
            Log.d(TAG, "segment Events: $events")
        }
    }
}