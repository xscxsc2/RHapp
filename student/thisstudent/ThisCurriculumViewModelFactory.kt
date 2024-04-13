package com.arcsoft.arcfacedemo.thisapp.student.thisstudent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.arcfacedemo.thisapp.teacher.curriculum.CurriculumViewModel

class ThisCurriculumViewModelFactory(private val userName: String) :
    ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ThisCurriculumViewModel::class.java)) {
                return ThisCurriculumViewModel(userName) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
