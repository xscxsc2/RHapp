package com.arcsoft.arcfacedemo.thisapp.loginorregister.register

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityRegisterBinding
import com.arcsoft.arcfacedemo.thisapp.base.activity.BaseTitleActivity
import com.arcsoft.arcfacedemo.thisapp.util.AlertDialogHelper

class RegisterActivity : BaseTitleActivity<ActivityRegisterBinding>() {

    private var viewModel: RegisterViewModel = RegisterViewModel()

    override fun initDatum() {
        super.initDatum()
        viewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)
        initViewModel(viewModel)
    }


    override fun initViews() {
        super.initViews()

        getWindow().apply {
//            statusBarColor = ContextCompat.getColor(applicationContext, R.color.)
            // 如果您使用的是 CoordinatorLayout，可能还需要添加以下代码来使 Toolbar 正确对齐：
            // window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

    }

    override fun initListeners() {
        super.initListeners()
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioTeacher -> {
                    binding.studentNo.setText("请输入工号")
//                    AlertDialogHelper.showAlertDialogSingle(this, "这是一个弹窗消息","确定")
                }

                R.id.radioStudent -> {
                    binding.studentNo.setText("请输入学号")
                    AlertDialogHelper.showAlertDialogDouble(this, "学生注册需要人脸！！","确定","取消", listener = object :
                        AlertDialogHelper.OnButtonClickListener {
                        override fun onLeftButtonClicked() {
                            Log.d(TAG, "onLeftButtonClicked: 取消")
                        }

                        override fun onRightButtonClicked() {
                            Log.d(TAG, "onRightButtonClicked: 确定")
                        }
                    })
                }
            }
        }


    }

    companion object{
        val TAG: String = "RegisterActivity"
    }

}







