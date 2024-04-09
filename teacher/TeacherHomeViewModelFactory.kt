package com.arcsoft.arcfacedemo.thisapp.teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TeacherHomeViewModelFactory(private val TeacherName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeacherHomeViewModel::class.java)) {
            return TeacherHomeViewModel(TeacherName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}