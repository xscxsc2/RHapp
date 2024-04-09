package com.arcsoft.arcfacedemo.thisapp.base.fragment

import com.arcsoft.app.base.activity.BaseLogicActivity
import com.arcsoft.app.base.viewmodel.BaseViewModel

abstract class BaseLogicFragment: BaseCommonFragment() {

    protected val hostActivity: BaseLogicActivity
        protected get() = requireActivity() as BaseLogicActivity


    /**
     * 初始化通用ViewModel逻辑
     */
    protected fun initViewModel(viewModel: BaseViewModel) {
        //关闭界面
        viewModel.finishPage.observe(this) {
            hostActivity.finish()
        }

        //本地提示
        viewModel.tip.observe(this) {
            onTip(it)
        }

        //异常
//        viewModel.exception.observe(this) {
//            onException(it)
//        }

        //网络响应业务失败
//        viewModel.response.observe(this) {
//            onResponse(it)
//        }

        //加载提示
        viewModel.loading.observe(this) {
        }
    }

    open fun onTip(data: Int) {
        hostActivity.onTip(data)
        onError()
    }

//    open fun onResponse(data: BaseResponse) {
//        hostActivity.onResponse(data)
//        onError()
//    }
//
//    open fun onException(data: Throwable) {
//        hostActivity.onException(data)
//        onError()
//    }

    open fun onError() {

    }

}