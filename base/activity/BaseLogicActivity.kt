package com.arcsoft.app.base.activity

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.app.extension.shortToast
import com.arcsoft.app.util.SuperDarkUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper


open class BaseLogicActivity : BaseCommonActivity() {

    protected val hostActivity: BaseLogicActivity
        protected get() = this


    override fun initViews() {
        super.initViews()
        //设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)
        if (SuperDarkUtil.isDark(this)) {
            //状态栏文字白色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            //状态栏文字黑色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }

//        val window = window
//        window.statusBarColor = getActivityBackgroundColor(this)
    }

//    fun getActivityBackgroundColor(activity: Activity): Int {
//        val decorView = activity.window.decorView
//        val backgroundDrawable = decorView.background
//        if (backgroundDrawable is ColorDrawable) {
//            return backgroundDrawable.color
//        }
//        // 如果背景不是 ColorDrawable，您可能需要处理其他类型的 Drawable
//        return Color.TRANSPARENT
//    }

    /**
     * 初始化通用ViewModel逻辑
     */
    protected fun initViewModel(viewModel: BaseViewModel) {
        //关闭界面
        viewModel.finishPage.observe(this) {
            finish()
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
//            if (StringUtils.isNotBlank(it)) showLoading(it) else hideLoading()
        }
    }

    open fun onTip(data: Int) {
        data.shortToast()
        onError()
    }

    open fun onError() {

    }

//    open fun onException(data: Throwable) {
//        if (NetworkUtils.isAvailableByPing()) {
//            //有网络
//            R.string.error_load.longToast()
//        } else {
//            //提示，你的网络好像不太好
//            R.string.error_network_not_connect.longToast()
//        }
//
////        when (data) {
////            is SocketException -> {
////                //例如：服务器没有启动
////                R.string.error_connect_server.longToast()
////            }
////
////            is UnknownHostException -> {
////                //域名无法解析，例如：域名写错了
////                R.string.error_unknown_host.longToast()
////            }
////
////            is SocketTimeoutException -> {
////                //连接超时，例如：网络特别慢
////                R.string.error_network_timeout.longToast()
////            }
////
////            is ConnectException -> {
////                //以下情况都会触发该异常：
////                //服务器没有开启
////                //本地网络关闭
////                if (SuperNetworkUtil.isNetworkConnected(hostActivity)) {
////                    //本地有网络
////
////                    //提示连接服务端失败
////                    R.string.error_connect_server.longToast()
////                } else {
////                    //本地没有网络
////
////                    //提示，你的网络好像不太好
////                    R.string.error_network_not_connect.longToast()
////                }
////            }
////
////            is HttpException -> {
////                //http异常，例如：服务端返回401，403
////                handleHttpError(data)
////            }
////
////            is IllegalArgumentException -> {
////                //本地参数错误
////                R.string.error_illegal_argument.shortToast()
////            }
//////            is ClientException -> {
//////                "阿里云OSS客户端错误：${data.localizedMessage}".longToast()
//////            }
//////            is ServiceException -> {
//////                "阿里云OSS服务端错误：${data.localizedMessage}".longToast()
//////            }
////            else -> {
////                R.string.error_unknown.shortToast()
////            }
////        }
//
//        onError()
//    }

//    //region 加载提示
//    /**
//     * 显示加载对话框
//     */
//    open fun showLoading(data: Int) {
//        showLoading(getString(data))
//    }
//
//    /**
//     * 显示加载对话框
//     */
//    open fun showLoading(data: String = getString(R.string.loading)) {
//        Log.d("TAG", "showLoading: " + data)
//        if (loadingDialog == null || loadingDialog!!.get() == null) {
//            loadingDialog = WeakReference(
//                IOSLoadingDialog()
//                    .setOnTouchOutside(false)
//
//            )
//        }
//
//        val dialogData = loadingDialog!!.get()
//        dialogData?.setHintMsg(data)
//        if (dialogData!!.dialog == null || !dialogData!!.dialog!!.isShowing) {
//            dialogData!!.show(supportFragmentManager, "LoadingDialog")
//        }
//    }
//
//    /**
//     * 隐藏加载对话框
//     */
//    fun hideLoading() {
//        loadingDialog?.get()?.dismiss()
//    }
//    //endregion

}