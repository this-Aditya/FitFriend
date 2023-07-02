package com.aditya.fitfriend_android.diffutils

import android.util.Log
import com.aditya.fitfriend_android.abstracts.AbstractYogaDiffutil
import com.aditya.fitfriend_android.models.Asana

private const val TAG = "AsanaDiffutil"

class AsanaDiffutil(oldAsanas: List<Asana>, newAsanas: List<Asana>) :
    AbstractYogaDiffutil<Asana>(oldAsanas, newAsanas) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.i(TAG, "areItemsTheSame: Asana Diffutils, overridden method")
        return oldYogas[oldItemPosition].id == newYogas[newItemPosition].id
    }
}