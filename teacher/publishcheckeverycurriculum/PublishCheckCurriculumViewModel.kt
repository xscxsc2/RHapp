package com.arcsoft.arcfacedemo.thisapp.teacher.publishcheckeverycurriculum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import kotlinx.coroutines.launch

class PublishCheckCurriculumViewModel(private val curriculum: Curriculum): BaseViewModel() {

    private val _ds = MutableLiveData<Int>()
    val ds: LiveData<Int> = _ds

    fun check() {
        viewModelScope.launch {

        }
    }


}


















