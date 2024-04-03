package com.arcsoft.app.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 所有Activity父类
 */
open class BaseActivity : AppCompatActivity() {
    /**
     * 找控件
     */
    protected open fun initViews() {}

    /**
     * 设置数据
     */
    protected open fun initDatum() {}

    /**
     * 设置监听器
     */
    protected open fun initListeners() {}

    /**
     * 在onCreate方法后面调用
     * @param savedInstanceState
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initDatum()
        initViews()
        initListeners()
    }
}