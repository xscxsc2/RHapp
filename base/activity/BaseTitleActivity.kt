package com.arcsoft.arcfacedemo.thisapp.base.activity

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.arcsoft.app.base.activity.BaseViewModelActivity
import com.arcsoft.arcfacedemo.R

/**
 * 通用标题界面
 */
open class BaseTitleActivity<VB : ViewBinding> : BaseViewModelActivity<VB>() {
    lateinit var toolbar: Toolbar

    override fun initViews() {
        super.initViews()
        toolbar = findViewById(R.id.toolbar_news)

        //初始化Toolbar
        setSupportActionBar(toolbar)

        //是否显示返回按钮
        if (isShowBackMenu()) {
            showBackMenu()
        }
    }

    /**
     * 显示返回按钮
     */
    protected open fun showBackMenu() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * 是否显示返回按钮
     * @return
     */
    protected open fun isShowBackMenu(): Boolean {
        return true
    }

    /**
     * 菜单点击了回调
     * @param item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                //Toolbar返回按钮点击
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}