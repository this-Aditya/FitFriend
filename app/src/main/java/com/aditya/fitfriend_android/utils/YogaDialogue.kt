package com.aditya.fitfriend_android.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

object YogaDialogue {
     fun showDialogueBox(yoga: String, context: Context, view: View, action0: NavDirections, action1: NavDirections) {

        val options = arrayOf("All $yoga", "Favourites")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select $yoga").setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        view.findNavController().navigate(action0) }

                    1 -> {
                        view.findNavController().navigate(action1) }
                }
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }
}