package com.arcsoft.arcfacedemo.thisapp.teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeacherHomeViewModel(private val TeacherName: String): BaseViewModel() {

    private val _publish = MutableLiveData<Int>()
    val publish: LiveData<Int> = _publish

    fun publish(curriculumName: String) {
        GlobalScope.launch {
            val result = OpenGaussHelper.publishCurriculum(curriculumName, TeacherName)
            withContext(Dispatchers.Main){
                if (result == 1){
                    _publish.postValue(R.string.publish_successful)
                }else{
                    _publish.postValue(R.string.publish_failed)
                }
            }
        }
    }


}