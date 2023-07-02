package com.aditya.fitfriend_android.abstracts

import androidx.recyclerview.widget.DiffUtil

open class AbstractYogaDiffutil<T>(val oldYogas: List<T>, val newYogas: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldYogas.size

    override fun getNewListSize(): Int = newYogas.size

    // Here we are returning true But it won't be called, it will be overriden by
    // implementing class
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldYogas[oldItemPosition] == newYogas[newItemPosition]
}
