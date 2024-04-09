package com.arcsoft.app.util

import com.arcsoft.app.staticdata.Constant
import com.tencent.mmkv.MMKV

object  PreferenceUtil {


    val p: MMKV by lazy {
        MMKV.defaultMMKV()!!
    }
    /**
     * 保存、获取当前是老师还是学生端
     */
    fun saveClient(value: String){
        p.encode(Constant.client,value)
    }
    fun getClient(): String{
        return p.decodeString(Constant.client,"没存什么端")!!
    }

    /**
     * 获取、保存用户登录的姓名和工号、学号
     */
    fun getUserName(): String {
        return p.decodeString(USER_NAME,"null")!!
    }
    fun getUserSno(): String {
        return p.decodeString(USER_SNO,"null")!!
    }

    fun saveUserName(value: String){
        p.encode(USER_NAME,value)
    }
    fun saveUserSno(value: String){
        p.encode(USER_SNO,value)
    }

    /**
     * 保存、获取用户人脸id ： face_id
     */
    fun saveFaceId(faceId: Long){
       p.encode(USER_FACE_ID, faceId)
    }
    fun getFaceId(): Long{
        return p.decodeLong(USER_FACE_ID,0)
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
        return p.decodeString(USER_NAME, Constant.ANONYMOUS)!!
    }

    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    private fun putBoolean(key: String, value: Boolean) {
        p.edit().putBoolean(key, value).apply()
    }


    private val USER_ID = "user"
    private val USER_NAME = "user_name"
    private val USER_SNO = "user_password"
    private val USER_FACE_ID = "face_id"


}