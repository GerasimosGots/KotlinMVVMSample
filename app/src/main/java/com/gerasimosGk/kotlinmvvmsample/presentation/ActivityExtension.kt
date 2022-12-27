package com.gerasimosGk.kotlinmvvmsample.presentation

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

fun Activity?.showAlertDialog(
    title: String? = null,
    message: String?,
    positiveButtonText: String?,
    negativeButtonText: String? = null,
    onPositiveClickListener: DialogInterface.OnClickListener?,
    onNegativeClickListener: DialogInterface.OnClickListener? = null,
) {
    val activity = this
    AlertDialog.Builder(activity).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(positiveButtonText, onPositiveClickListener)

        negativeButtonText?.let {
            setNegativeButton(negativeButtonText, onNegativeClickListener)
        }

        if (activity != null && !activity.isFinishing)
            show()
    }
}