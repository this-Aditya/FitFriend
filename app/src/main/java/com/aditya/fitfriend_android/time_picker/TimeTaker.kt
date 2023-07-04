package com.aditya.fitfriend_android.time_picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.aditya.fitfriend_android.ui.landing_fragments.DashboardFragment
import java.util.Calendar

private const val TAG = "TimeTaker"

class TimeTaker : DialogFragment(), TimePickerDialog.OnTimeSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cl = Calendar.getInstance()
        val hour = cl.get(Calendar.HOUR)
        val minute = cl.get(Calendar.MINUTE)

        return TimePickerDialog(
            requireContext(), this, hour, minute,
            DateFormat.is24HourFormat(context)
        )
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.d(TAG, "onTimeSet: TimeKeeper")
        val fragment = parentFragment
        if (fragment is DashboardFragment) {
            // Call the onTimeSet method of DashboardFragment
            fragment.onTimeSet(view, hourOfDay, minute)
        }
    }
}