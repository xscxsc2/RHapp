package com.arcsoft.arcfacedemo.thisapp.util

import android.util.Log
import kotlin.random.Random

object CodeUtil {

    private val random = Random(System.currentTimeMillis())

    fun getCurriculumCode(): String {
        val chars = ('0'..'9') + ('A'..'Z')
        return (1..4).map {
            chars.random(random)
        }.joinToString("")
    }

    val TAG = "CodeUtil"

}



