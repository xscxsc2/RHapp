package com.arcsoft.arcfacedemo.thisapp.loginorregister.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.thisapp.bean.Teacher
import com.arcsoft.arcfacedemo.thisapp.bean.User
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class LoginViewModel : BaseViewModel() {

    private var _message = MutableLiveData<Int>()
    val message: LiveData<Int> = _message

    fun LoginFromStudent(userSno: String?, userPassword: String) {
        GlobalScope.launch {
            var user = OpenGaussHelper.SelectUserBySno(userSno.toString().toLong())
            Log.d(TAG, "${user.toString()}")
            withContext(Dispatchers.Main) { // 切换到主线程
                if (user == null) {
                    _message.postValue(R.string.UserNotExistence)
                } else if (userPassword != user.password) {
                    _message.value = R.string.PasswordIncorrect
                } else {
                    _message.postValue(R.string.login_successful)
                    localSave(user)
                }

            }
        }
    }

    fun LoginFromTeacher(Sno: String, password: String) {
        viewModelScope.launch {
            var teacher = OpenGaussHelper.SelectTeacherBySno(Sno.toLong())
            Log.d(TAG, "老师的名字:${teacher.toString()}")
            withContext(Dispatchers.Main){
                if (teacher == null) {
                    _message.postValue(R.string.UserNotExistence)
                } else if (password != teacher.password) {
                    _message.value = R.string.PasswordIncorrect
                } else {
                    _message.postValue(R.string.login_successful)
                    localSave(teacher)
                }
            }
        }
    }

    private fun localSave(any: Any) {
        if (any is User){
            Log.d(TAG, "登录的是学生: ${any.toString()}")
            PreferenceUtil.saveUserName(any.inputName!!)
            PreferenceUtil.saveUserSno(any.snoId.toString()!!)
            PreferenceUtil.saveFaceId(any.faceId!!)
            PreferenceUtil.saveClient(Constant.clientStudent)
        }else if (any is Teacher){
            Log.d(TAG, "登录的是老师: ${any.toString()}")
            PreferenceUtil.saveUserName(any.inputName!!)
            PreferenceUtil.saveUserSno(any.snoId.toString()!!)
            PreferenceUtil.saveClient(Constant.clientTeacher)
        }

    }


    companion object {
        const val TAG = "LoginViewModel"
    }


}













