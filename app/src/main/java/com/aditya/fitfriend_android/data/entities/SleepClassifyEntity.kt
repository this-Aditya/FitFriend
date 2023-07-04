package com.aditya.fitfriend_android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.location.SleepClassifyEvent

@Entity("sleep_classify")
data class SleepClassifyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    val timeStamp: Long,

    @ColumnInfo("confidence")
    val confidence: Int
) {
    companion object {
        fun from(sleepClassifyEvent: SleepClassifyEvent): SleepClassifyEntity =
            SleepClassifyEntity(
                sleepClassifyEvent.timestampMillis,
                sleepClassifyEvent.confidence
            )
    }
}