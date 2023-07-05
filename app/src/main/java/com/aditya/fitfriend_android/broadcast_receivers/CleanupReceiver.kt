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