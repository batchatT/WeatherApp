package com.example.weatherapp.commonWeather

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.R

class ErrorFromResponseDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.error_text))
            .setPositiveButton(getString(R.string.ok)) { _,_ -> }
            .create()

    companion object {
        const val TAG = "ErrorFromResponseDialog"
    }
}
