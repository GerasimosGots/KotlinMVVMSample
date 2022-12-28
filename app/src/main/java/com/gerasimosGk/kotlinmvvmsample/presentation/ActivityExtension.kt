package com.gerasimosGk.kotlinmvvmsample.presentation

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import com.gerasimosGk.kotlinmvvmsample.R

fun Activity?.showAlertDialog(
    title: String? = null,
    message: String?,
    positiveButtonText: String?,
    onPositiveClickListener: DialogInterface.OnClickListener?
) {
    val activity = this
    if (this == null) {
        return
    }

    val builder = AlertDialog.Builder(this)
    with(builder) {
        setTitle(title)
        setMessage(message)
        setCancelable(true)
        setPositiveButton(positiveButtonText, onPositiveClickListener)

        this.create()
    }.run {

        //TODO
        /*getButton(DialogInterface.BUTTON_POSITIVE).apply {
            setTextColor(Color.MAGENTA)
        }*/

        if (activity != null && !activity.isFinishing){
            show()
        }
    }
}