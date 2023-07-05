package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.models.Pranayam

class PranayamDiffutil(val oldPranayams: List<Pranayam>, val newPranayams: List<Pranayam>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldPranayams.size

    override fun getNewListSize(): Int = newPranayams.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldPranayams[oldItemPosition].id == newPranayams[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldPranayams[oldItemPosition] == newPranayams[newItemPosition]
}