package com.arcsoft.arcfacedemo.thisapp.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes

abstract class BaseCommonFragment: BaseFragment() {

    fun <T : View?> findViewById(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }


    /**
     * 获取int值
     *
     * @param key
     * @return
     */
    protected fun extraInt(key: String?): Int {
        return requireArguments().getInt(key, -1)
    }

}















