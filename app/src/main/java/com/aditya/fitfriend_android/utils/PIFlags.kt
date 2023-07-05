package com.aditya.fitfriend_android.utils

import android.app.PendingIntent
import android.os.Build
import android.os.Build.VERSION_CODES

fun Int.toPendingIntentFlag(mutable: Boolean = false) = this or when {
    mutable && Build.VERSION.SDK_INT >= VERSION_CODES.S -> PendingIntent.FLAG_MUTABLE
    !mutable -> PendingIntent.FLAG_IMMUTABLE
    else -> 0
}