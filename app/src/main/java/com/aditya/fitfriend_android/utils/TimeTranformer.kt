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