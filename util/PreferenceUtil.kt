package com.arcsoft.app.util

import com.arcsoft.app.staticdata.Constant
import com.tencent.mmkv.MMKV

object  PreferenceUtil {

    val p: MMKV by lazy {
        MMKV.defaultMMKV()!!
    }


    /**
     * 是否登录了
     *
     * @return
     */
    fun isLogin(): Boolean {
        return Constant.ANONYMOUS != getUserId()
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    fun getUserId(): String {
        return p.decodeString(USER_ID, Constant.ANONYMOUS)!!
    }

    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    private fun putBoolean(key: String, value: Boolean) {
        p.edit().putBoolean(key, value).apply()
    }


    private val USER_ID = "user"

}