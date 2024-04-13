package com.arcsoft.arcfacedemo.thisapp.student.thisstudent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import com.arcsoft.arcfacedemo.thisapp.teacher.curriculum.CurriculumZ
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThisCurriculumViewModel(private val StudentName: String): BaseViewModel() {


    private val _ds = MutableLiveData<List<CurriculumZ>>()
    val ds: LiveData<List<CurriculumZ>> = _ds


    // 加载数据的函数
    fun loadData() {
        viewModelScope.launch {
            val newData = OpenGaussHelper.selectAllCurriculumByStudentName(StudentName)
            newData?.let {
                Log.d(TAG, "loadData: ${it.toString()}")
                _ds.postValue(it)
            }
        }
    }

    fun joinCurriculum(inputCode: String, studentName: String) {
        Log.d(TAG, "joinCurriculum: ${inputCode}")
        GlobalScope.launch {
            val result_joinStudentTable = OpenGaussHelper.joinCurriculum(inputCode, studentName)//添加到该学生的所有课程中
            val result_joinTable = OpenGaussHelper.result_joinTable(inputCode.toLowerCase(), PreferenceUtil.getUserName(), PreferenceUtil.getUserSno())//添加到此课程的所有学生中
            if (result_joinStudentTable == 1 && result_joinTable == 1){
                Log.d(TAG, "joinCurriculum: 加入成功")
            }else{
                Log.d(TAG, "joinCurriculum: 加入成功")
            }
        }

    }

    companion object{
        const val TAG = "ThisCurriculumViewModel"
    }

}












