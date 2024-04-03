package com.arcsoft.arcfacedemo.thisapp.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object AlertDialogHelper {

    fun showAlertDialogSingle(context: Context, message: String, str1: String, listener: OnSingleClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(str1) { dialog, _ ->
            listener.Click()
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showAlertDialogDouble(context: Context, message: String, str1: String, str2: String, listener: OnButtonClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(str1) { dialog, _ ->
            listener.onRightButtonClicked()
            dialog.dismiss()
        }
        builder.setNegativeButton(str2) { dialog, _ ->
            listener.onLeftButtonClicked()
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    public interface OnButtonClickListener {
        fun onLeftButtonClicked()
        fun onRightButtonClicked()
    }

    public interface OnSingleClickListener{
        fun Click()
    }
}
