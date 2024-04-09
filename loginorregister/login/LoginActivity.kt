package com.arcsoft.arcfacedemo.thisapp.loginorregister.login

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.app.base.activity.BaseViewModelActivity
import com.arcsoft.app.extension.shortToast
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityLoginBinding
import com.arcsoft.arcfacedemo.thisapp.loginorregister.register.RegisterActivity

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>() {

    private lateinit var viewModel: LoginViewModel
    private var CheckStudentOrTeacher = Constant.isCheckTeacher

    override fun initDatum() {
        super.initDatum()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.message.observe(this) {
            Log.d(TAG, "initDatum: ${getString(it)}")
            getString(it).shortToast()
        }

    }


    override fun initListeners() {
        super.initListeners()
        binding.register.setOnClickListener {
            startActivity(RegisterActivity::class.java)
        }

        binding.login.setOnClickListener {
            if (binding.userSno.text.toString() == "" || binding.userPassword.text.toString() == "") {
                "请输入完整信息".shortToast()
                return@setOnClickListener
            }
            if (CheckStudentOrTeacher == Constant.isCheckStudent) {
                viewModel.LoginFromStudent(
                    binding.userSno.text.toString(),
                    binding.userPassword.text.toString()
                )
            } else {
                viewModel.LoginFromTeacher(
                    binding.userSno.text.toString(),
                    binding.userPassword.text.toString()
                )
            }

        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioTeacher -> {
                    CheckStudentOrTeacher = Constant.isCheckTeacher
                }

                R.id.radioStudent -> {
                    CheckStudentOrTeacher = Constant.isCheckStudent
                }
            }
        }

    }

    companion object {
        const val TAG = "LoginActivity"
    }

}