package com.aditya.fitfriend_android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.location.SleepSegmentEvent

@Entity("sleep_segment")
data class SleepSegmentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("start_time")
    val startTime:Long,

    @ColumnInfo("end_time")
    val endTime: Long,

    @ColumnInfo("sleep_duration")
    val duration: Long
) {
    companion object {
        fun from(sleepSegmentEvent: SleepSegmentEvent): SleepSegmentEntity =
            SleepSegmentEntity(
                sleepSegmentEvent.startTimeMillis,
                sleepSegmentEvent.endTimeMillis,
                sleepSegmentEvent.segmentDurationMillis
            )
    }
}