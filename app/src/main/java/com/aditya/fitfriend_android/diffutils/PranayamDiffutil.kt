package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.models.Pranayam

class PranayamDiffutil(val oldPranayams: List<Pranayam>, val newPranayams: List<Pranayam>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldPranayams.size

    override fun getNewListSize(): Int = newPranayams.size

    // Here we are returning true But it won't be called, it will be overriden by
    // implementing class
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldPranayams[oldItemPosition] == newPranayams[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldPranayams[oldItemPosition] == newPranayams[newItemPosition]
}