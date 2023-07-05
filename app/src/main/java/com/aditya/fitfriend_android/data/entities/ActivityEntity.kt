package com.aditya.fitfriend_android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.fitfriend_android.models.ActivityType
import com.aditya.fitfriend_android.models.Transition
import com.google.android.gms.location.DetectedActivity

@Entity("activity")
data class ActivityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("time")
    val time: Long,

    @ColumnInfo("activity_name")
    val activityName: ActivityType,

    @ColumnInfo("transition")
    val transition: Transition
) {
    companion object {
        fun toActivityType(activity: Int): ActivityType = when(activity) {
            DetectedActivity.STILL -> ActivityType.STILL
            DetectedActivity.IN_VEHICLE -> ActivityType.VEHICLE
            DetectedActivity.ON_FOOT -> ActivityType.WALKING
            DetectedActivity.RUNNING -> ActivityType.RUNNING
            DetectedActivity.ON_BICYCLE -> ActivityType.BICYCLE
            else -> {ActivityType.UNKNOWN}
        }
    }
}