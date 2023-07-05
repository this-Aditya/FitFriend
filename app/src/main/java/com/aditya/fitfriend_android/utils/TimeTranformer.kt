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

package com.aditya.fitfriend_android.utils

import java.text.DateFormat
import java.util.Date
import java.util.Locale

class TimeTranformer {

    companion object {
        fun epochToDateTime(timeMilis: Long): String {
            val date = Date(timeMilis)
            val dateFormat =
                DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US)
            return dateFormat.format(date)
        }

        fun convertMsToHMS(milliseconds: Long): String {
            val seconds = milliseconds / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            return "$hours:${minutes % 60}:${seconds % 60}"
        }

    }
}