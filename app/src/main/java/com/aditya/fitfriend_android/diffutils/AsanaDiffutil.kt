package com.aditya.fitfriend_android.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.aditya.fitfriend_android.models.Asana

private const val TAG = "AsanaDiffutil"

class AsanaDiffutil(val oldAsanas: List<Asana>,val newAsanas: List<Asana>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldAsanas.size

    override fun getNewListSize(): Int = newAsanas.size

    // Here we are returning true But it won't be called, it will be overriden by
    // implementing class
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldAsanas[oldItemPosition] == newAsanas[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldAsanas[oldItemPosition] == newAsanas[newItemPosition]
}
