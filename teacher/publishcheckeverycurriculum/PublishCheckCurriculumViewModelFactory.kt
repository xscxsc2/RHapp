package com.arcsoft.arcfacedemo.thisapp.teacher.publishcheckeverycurriculum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import com.arcsoft.arcfacedemo.thisapp.teacher.TeacherHomeViewModel

class PublishCheckCurriculumViewModelFactory(private val curriculum: Curriculum): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PublishCheckCurriculumViewModel::class.java)) {
            return PublishCheckCurriculumViewModel(curriculum) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}