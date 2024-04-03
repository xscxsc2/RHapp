package com.arcsoft.arcfacedemo.thisapp.opengauss

import android.util.Log
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.facedb.entity.FaceEntity
import org.greenrobot.eventbus.EventBus
import java.sql.DriverManager

object OpenGaussHelper {

    const val url = Constant.url          // 这里替换成你的数据库URL
    const val DataBaseName = Constant.username                                                  // 替换成你的用户名
    const val DataBasePassword = Constant.userpassword                                  // 替换成你的密码

     @JvmStatic
     fun registerUser(faceEntity: FaceEntity, name: String, password: String, sno: String) {
        val conn = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
        var rowsAffected = 0
        var error: String? = null
        Log.d(TAG, "registerUser")
        try {
            // 插入新用户记录到 users 表格
//            val statement = conn.prepareStatement("INSERT INTO users (用户名, 密码) VALUES (?, ?)")
//            statement.setString(1, username)
//            statement.setString(2, password)
//            val byteArray = ByteArray(1024) // 创建一个长度为1024的字节数组
//
//            // 随机填充字节数组
//            for (i in byteArray.indices) {
//                byteArray[i] = Random.nextInt(256).toByte() // 随机生成一个0到255之间的整数，并转换为字节
//            }
            val statement = conn.prepareStatement("INSERT INTO face_table (sno_id, user_name, password, image_path, feature_data, register_time, input_username) VALUES (?, ?, ?, ?, ?, ?,?)")
            statement.setLong(1, sno.toLong());
            statement.setString(2, faceEntity.userName);
            statement.setString(3, password);
            statement.setString(4, faceEntity.imagePath);
            statement.setBytes(5, faceEntity.featureData);
            Log.d("faceEntity.featureData.get(0)", "${faceEntity.featureData.get(0)} ")
            statement.setLong(6, faceEntity.registerTime);
            statement.setString(7, name);
            rowsAffected = statement.executeUpdate()

            Log.d(TAG, "${statement} ")
            Log.d(TAG, "${rowsAffected} ")
            //发布事件
            EventBus.getDefault().postSticky(rowsAffected )
        } catch (e: Exception) {
            // 记录异常并返回
            Log.e("xsc", "Error inserting user: $e")
        } finally {
            // 关闭数据库连接
            conn.close()
        }
    }
    const val TAG = "OpenGaussHelper"
}