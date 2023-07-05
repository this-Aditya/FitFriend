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

package com.aditya.fitfriend_android.adapters

import android.graphics.RectF
import com.aditya.fitfriend_android.models.DataLog
import com.aditya.fitfriend_android.models.DataLog.MAX
import com.aditya.fitfriend_android.models.Sleep
import com.aditya.fitfriend_android.models.SleepMetric.SLEEP_CLASSIFY
import com.aditya.fitfriend_android.models.SleepMetric.SLEEP_SEGMENTS
import com.robinhood.spark.SparkAdapter

class SleepSparkAdapter(val sleep: Sleep) : SparkAdapter() {

    var log = MAX
    var metric = SLEEP_CLASSIFY

    override fun getCount(): Int = when (metric) {
        SLEEP_SEGMENTS -> sleep.segment.size
        SLEEP_CLASSIFY -> sleep.classify.size
    }

    override fun getItem(index: Int): Any = when (metric) {
        SLEEP_SEGMENTS -> try {
            sleep.segment[index]
        } catch (ex: Exception) {
            sleep.segment.last()
        }
        SLEEP_CLASSIFY -> try {
            sleep.classify[index]
        } catch (ex: Exception) {
            sleep.classify.last()
        }
    }

    override fun getY(index: Int): Float {
        return when (metric) {
            SLEEP_CLASSIFY -> sleep.classify[index].confidence.toFloat()
            SLEEP_SEGMENTS -> sleep.segment[index].duration.toFloat()
        }
    }

    override fun getDataBounds(): RectF {
        val bounds = super.getDataBounds()
        if (log != MAX) {
            if (count > log.numLogs) {
                bounds.left = (count - log.numLogs).toFloat()
            } else {
                return bounds
            }
        }
        return bounds
    }
}