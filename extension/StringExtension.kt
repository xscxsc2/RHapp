package com.arcsoft.app.extension

import android.widget.Toast
import com.arcsoft.arcfacedemo.ArcFaceApplication

/**
 * Int 扩展toast
 */

/**
 * 短toast
 */
fun String.shortToast() {
    Toast.makeText(ArcFaceApplication.getApplication(), this, Toast.LENGTH_SHORT).show()
}

/**
 * 长toast
 */
fun String.longToast() {
    Toast.makeText(ArcFaceApplication.getApplication(), this, Toast.LENGTH_LONG).show()
}

/**
 * 短toast
 *
 * 加了reified，才能获取t的class，这是kotlin语法
 */
//inline fun <reified T> String.toObject(): T {
//    //通过gson转换
//    return GsonBuilder()
//        .create()
//        .fromJson(this, T::class.java)
//}

/**
 * 是否是url
 */
fun String.isUrl(): Boolean {
    return startsWith("http://") || startsWith("https://")
}