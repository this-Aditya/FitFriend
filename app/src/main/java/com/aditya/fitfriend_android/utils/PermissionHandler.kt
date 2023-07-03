package com.aditya.fitfriend_android.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private const val TAG = "PermissionHandler"

/**
 * Utility permission handler, for asking for the permission just create the instance of the class
 * and call the `requestPermissions` Method.
 */
class PermissionHandler {

    /**
     * This mehod should be called in order to request for permissions.
     * @param context context required for permissions (should be the context of an activity.)
     * @param permissions list of permissions
     * @param launcher launcher which is launching the permission dialogue
     * @param message message to be displayed as a rationale when user once declines permission
     */
    fun requestPermissions(
        context: Activity,
        permissions: Array<String>,
        launcher: ActivityResultLauncher<Array<String>>,
        message: String
    ) {
        val permissionsToRequest = mutableListOf<String>()

        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.i(TAG, "Permission Needed: $permission")
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.isEmpty()) {
            Log.i(TAG, "All required permissions are granted.")
        } else {
            Log.i(TAG, "All permissions are not granted yet.")

            val shouldShowRationale = permissionsToRequest.any { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(context, permission)
            }
            if (shouldShowRationale) {
                Toast.makeText(context, "All permissions are not granted yet!", Toast.LENGTH_SHORT)
                    .show()
                showAlertDialogue(context, permissionsToRequest.toTypedArray(), launcher, message)
            } else {
                // permissions haven't been asked yet!
                launcher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }

    private fun showAlertDialogue(
        context: Context,
        permissions: Array<String>,
        launcher: ActivityResultLauncher<Array<String>>,
        message: String
    ) {
        AlertDialog.Builder(context).apply {
            setTitle("Need permissions for smooth experience.")
            setMessage(message)
            setPositiveButton("Yes") { _,_ ->
                launcher.launch(permissions)
            }
            setNegativeButton("No") { _,_ ->
                Log.w(TAG, "Permissions $permissions denied.", )
            }
        }.create().show()
    }
}