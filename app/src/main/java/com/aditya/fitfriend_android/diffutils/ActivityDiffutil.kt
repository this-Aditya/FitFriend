package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.data.entities.ActivityEntity

class ActivityDiffutil(
    private val oldActivities: List<ActivityEntity>, private val newActivities: List<ActivityEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldActivities.size

    override fun getNewListSize(): Int = newActivities.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldActivities[oldItemPosition].time == newActivities[newItemPosition].time

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldActivities[oldItemPosition] == newActivities[newItemPosition]
}