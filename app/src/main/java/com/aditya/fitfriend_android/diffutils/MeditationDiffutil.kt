package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.models.Meditation

class MeditationDiffutil(private val oldMeditations: List<Meditation>, private val newMeditations: List<Meditation>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldMeditations.size

    override fun getNewListSize(): Int = newMeditations.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMeditations[oldItemPosition] == newMeditations[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldMeditations[oldItemPosition] == newMeditations[newItemPosition]
}