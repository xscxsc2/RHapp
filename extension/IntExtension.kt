package com.arcsoft.app.extension

import android.widget.Toast
import com.arcsoft.arcfacedemo.ArcFaceApplication

/**
 * Int 扩展toast
 */

/**
 * 短toast
 */
fun Int.shortToast() {
    Toast.makeText(ArcFaceApplication.getApplication(), this, Toast.LENGTH_SHORT).show()
}

/**
 * 长toast
 */
fun Int.longToast() {
    Toast.makeText(ArcFaceApplication.getApplication(), this,Toast.LENGTH_LONG).show()
}