package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 内容VM的工厂类
 */
class CurriculumViewModelFactory(private val teacherName: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurriculumViewModel::class.java)) {
            return CurriculumViewModel(teacherName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}