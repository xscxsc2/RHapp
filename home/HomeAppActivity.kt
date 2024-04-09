package com.arcsoft.arcfacedemo.thisapp.home

import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.arcsoft.app.base.activity.BaseViewModelActivity
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityMainContentBinding
import com.arcsoft.arcfacedemo.databinding.ItemTabBinding


class HomeAppActivity : BaseViewModelActivity<ActivityMainContentBinding>() {

    private lateinit var viewModel: HomeAppViewModel
    private lateinit var client: String


    override fun initViews() {
        super.initViews()


    }


    override fun initDatum() {
        super.initDatum()
        viewModel =
            ViewModelProvider(this).get(HomeAppViewModel::class.java)
        initViewModel(viewModel)

        //获取端

        client = PreferenceUtil.getClient()
        Log.d(TAG, "initDatum:当前端 ${client}")

        //滚动控件
        binding.apply {
            pager.offscreenPageLimit = indicatorTitles.size
            pager.adapter = HomeAppAdapter(this@HomeAppActivity, indicatorTitles.size, client)
        }
        //底部tab
        for (i in indicatorTitles.indices) {
            ItemTabBinding.inflate(layoutInflater).apply {
                content.setText(indicatorTitles[i])
                icon.setImageResource(indicatorIcons[i])
                binding.indicator.addView(root)
            }
        }
        ViewPager2Delegate.install(binding.pager, binding.indicator, false)
    }


    companion object {
        const val TAG = "HomeAppActivity"
        /**
         * 底部指示器（tab）文本，图标，选中的图标
         */
        private val indicatorTitles =
            intArrayOf(
                R.string.curriculum,
                R.string.me
            )
        private val indicatorIcons = intArrayOf(
            R.drawable.selector_tab_curriculum,
            R.drawable.selector_tab_me
        )
    }

}