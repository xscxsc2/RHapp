package com.arcsoft.arcfacedemo.thisapp.loginorregister.register

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.arcfacedemo.thisapp.bean.Teacher
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class RegisterViewModel : BaseViewModel() {

    private val _data = MutableLiveData<Int>()
    val data: LiveData<Int> = _data

    fun register() {

    }

    fun registerFromTeacher(nickname: EditText, password: EditText, studentNo: EditText) {
        var name: String = nickname.text.toString()
        var password: String = password.text.toString()
        var snoId: Long = studentNo.text.toString().toLong()
        val teacher = Teacher(name, snoId, password)
        // 在非主线程调用 OpenGaussHelper.registerFromTeacher(teacher)
        GlobalScope.launch {
            val result = OpenGaussHelper.registerFromTeacher(teacher)

            // 在主线程更新 _data.value 的值
            CompletableFuture.runAsync {
                _data.postValue(result)
            }
        }

    }


}