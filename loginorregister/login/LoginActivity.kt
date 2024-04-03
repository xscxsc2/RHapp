package com.arcsoft.arcfacedemo.thisapp.loginorregister.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arcsoft.app.base.activity.BaseViewModelActivity
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityLoginBinding
import com.arcsoft.arcfacedemo.thisapp.loginorregister.register.RegisterActivity

class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>() {


    override fun initListeners() {
        super.initListeners()
        binding.register.setOnClickListener{
            startActivity(RegisterActivity::class.java)
        }
    }
}