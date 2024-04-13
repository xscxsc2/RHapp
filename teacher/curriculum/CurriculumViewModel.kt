package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CurriculumViewModel(private val teacherName: String) : BaseViewModel() {

    private val _ds = MutableLiveData<List<Curriculum>>()
    val ds: LiveData<List<Curriculum>> = _ds


    // 加载数据的函数
    fun loadData() {
        // 如果数据已加载过，则不执行加载操作
        viewModelScope.launch {
            val newData = OpenGaussHelper.selectAllCurriculumByTeacherName(teacherName)
            newData?.let {
                Log.d(TAG, "loadData: ${it.toString()}")
                _ds.postValue(it)
            }
        }
    }


}



















