package com.arcsoft.arcfacedemo.thisapp.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.arcfacedemo.thisapp.teacher.TeacherHomeViewModel

class StudentHomeViewModelFactory(private val StudentName: String): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentHomeViewModel::class.java)) {
            return StudentHomeViewModel(StudentName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}