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

import android.app.PendingIntent
import android.os.Build
import android.os.Build.VERSION_CODES

fun Int.toPendingIntentFlag(mutable: Boolean = false) = this or when {
    mutable && Build.VERSION.SDK_INT >= VERSION_CODES.S -> PendingIntent.FLAG_MUTABLE
    !mutable -> PendingIntent.FLAG_IMMUTABLE
    else -> 0
}