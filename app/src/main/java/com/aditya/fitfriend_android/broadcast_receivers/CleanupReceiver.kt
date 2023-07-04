package com.aditya.fitfriend_android.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.aditya.fitfriend_android.repository.YogaRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class receives data every 24 hours at nearly 12:00 am to clear cached sleep classify events.
 */

@AndroidEntryPoint
class CleanupReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: YogaRepository
    private val scope = MainScope()

    override fun onReceive(context: Context?, intent: Intent?) {
        scope.launch {
            repository.deleteSleepClassifyEvents()
        }
    }
}