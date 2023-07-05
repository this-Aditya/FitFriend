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
                    result.transitionEvents.forEach {
                        val time = System.currentTimeMillis()
                        val activity = ActivityEntity.toActivityType(it.activityType)
                        val transition = ActivityEntity.toTransitionType(it.transitionType)
                        activities.add(ActivityEntity(time, activity, transition))
                    }
                    scope.launch {
                        repository.insertActivities(activities)
                        Log.i(TAG, "Inserted activity updates to database.")
                    }
                }
            }
        }
    }
}
