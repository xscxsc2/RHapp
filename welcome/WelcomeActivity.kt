package com.arcsoft.arcfacedemo.thisapp.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arcsoft.app.base.activity.BaseViewModelActivity
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityWelcomeBinding
import com.arcsoft.arcfacedemo.thisapp.loginorregister.login.LoginActivity
import com.arcsoft.arcfacedemo.ui.activity.HomeActivity

class WelcomeActivity : BaseViewModelActivity<ActivityWelcomeBinding>() {

    override fun initViews() {
        super.initViews()

        binding.root.postDelayed({
            prepareNext()
        }, 1500)

    }

    private fun prepareNext() {
        val intent = Intent()
        if (PreferenceUtil.isLogin()) {
            intent.setClass(hostActivity, HomeActivity::class.java)
        } else {
            intent.setClass(hostActivity, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }


}