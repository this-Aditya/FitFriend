package com.aditya.fitfriend_android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.fitfriend_android.models.ActivityType
import com.aditya.fitfriend_android.models.Transition

@Entity("activity")
data class ActivityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("time")
    val time: Long,

    @ColumnInfo("activity_name")
    val activityType: Int,

    @ColumnInfo("transition")
    val transition: Int
) {
    companion object {
        fun toActivityType(activity: Int): ActivityType = when(activity) {
            0 -> ActivityType.IN_VEHICLE
            1 -> ActivityType.IN_BICYCLE
            2 -> ActivityType.ON_FOOT
            3 -> ActivityType.ACTIVITY_STILL
            4 -> ActivityType.UNKNOWN
            5 -> ActivityType.TITLTING
            7 -> ActivityType.WALKING
            8 -> ActivityType.RUNNING
            else -> {ActivityType.UNKNOWN}
        }

        fun toTransitionType(transtion: Int): Transition = when(transtion) {
            0 -> Transition.STARTED
            1 -> Transition.STOPPED
            else -> Transition.STOPPED
        }
    }
}