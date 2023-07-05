/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

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