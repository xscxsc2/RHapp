package com.arcsoft.arcfacedemo.thisapp.bean

data class User(
    val faceId: Long?,
    val inputName: String?,
    val snoId: Long?,
    val userName: String?,
    val password: String?,
    val imagePath: String?,
    val featureData: ByteArray?,
    val registerTime: Long?,
)

