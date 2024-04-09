package com.arcsoft.arcfacedemo.thisapp.opengauss

import android.util.Log
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.facedb.entity.FaceEntity
import com.arcsoft.arcfacedemo.thisapp.bean.Teacher
import com.arcsoft.arcfacedemo.thisapp.bean.User
import com.arcsoft.arcfacedemo.thisapp.util.CodeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

object OpenGaussHelper {

    const val url = Constant.url          // 这里替换成你的数据库URL
    const val DataBaseName = Constant.DBusername                                                  // 替换成你的用户名
    const val DataBasePassword = Constant.DBuserpassword                                  // 替换成你的密码

     @JvmStatic
     fun registerUser(faceEntity: FaceEntity, name: String, password: String, sno: String) {
        val conn = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
        var rowsAffected = 0
        var error: String? = null
        Log.d(TAG, "registerUser")
        try {
            val statement = conn.prepareStatement("INSERT INTO student (face_id, input_username, sno_id, user_name, password, image_path, feature_data, register_time ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
            statement.setLong(1,faceEntity.faceId);
            statement.setString(2, name);
            statement.setLong(3, sno.toLong());
            statement.setString(4, faceEntity.userName);
            statement.setString(5, password);
            statement.setString(6, faceEntity.imagePath);
            statement.setBytes(7, faceEntity.featureData);
            Log.d("faceEntity.featureData.get(0)", "${faceEntity.featureData.get(0)} ")
            statement.setLong(8, faceEntity.registerTime);

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

    //通过学号查找学生
    suspend fun SelectUserBySno(snoId: Long): User? = withContext(Dispatchers.IO) {
        // 确保已经添加了 PostgreSQL 驱动到项目依赖中
        Class.forName("org.postgresql.Driver")
        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            connection = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
            val query = "SELECT * FROM student WHERE sno_id = ?"
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setLong(1, snoId)
            resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val faceId = resultSet.getLong("face_id")
                val inputName = resultSet.getString("input_username")
                val userName = resultSet.getString("user_name")
                val password = resultSet.getString("password")
                val imagePath = resultSet.getString("image_path")
                val featureData = resultSet.getBytes("feature_data")
                val registerTime = resultSet.getLong("register_time")
                User(faceId, inputName, snoId, userName, password, imagePath, featureData, registerTime, )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            resultSet?.close()
            preparedStatement?.close()
            connection?.close()
        }
    }

    //通过工号查找老师
    suspend fun SelectTeacherBySno(snoId: Long): Teacher? = withContext(Dispatchers.IO) {
        // 确保已经添加了 PostgreSQL 驱动到项目依赖中
        Class.forName("org.postgresql.Driver")
        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            connection = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
            val query = "SELECT * FROM teacher WHERE sno_id = ?"
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setLong(1, snoId)
            resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val inputName = resultSet.getString("input_username")
                val password = resultSet.getString("password")

                Teacher(inputName, snoId, password)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            resultSet?.close()
            preparedStatement?.close()
            connection?.close()
        }
    }

    @JvmStatic
    fun registerFromTeacher(teacher: Teacher): Int {
        val conn = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
        var rowsAffected = 0
        var error: String? = null
        Log.d(TAG, "registerUser")
        try {
            val statement = conn.prepareStatement("INSERT INTO teacher (input_username, sno_id,password) VALUES (?, ?, ?)")
            statement.setString(1,teacher.inputName);
            statement.setLong(2, teacher.snoId!!);
            statement.setString(3, teacher.password);
            rowsAffected = statement.executeUpdate()

            Log.d(TAG, "${statement} ")
            Log.d(TAG, "${rowsAffected} ")

            return rowsAffected

        } catch (e: Exception) {
            // 记录异常并返回
            Log.e("xsc", "Error inserting user: $e")
            return 0
        } finally {
            // 关闭数据库连接
            conn.close()
        }
    }

    fun publishCurriculum(curriculumName: String, teacherName: String): Int {
        val conn = DriverManager.getConnection(url, DataBaseName, DataBasePassword)
        var rowsAffected = 0
        var error: String? = null
        Log.d(TAG, "publishCurriculum")
        try {
            val statement = conn.prepareStatement("INSERT INTO curriculum (code, owner_name, curriculum_name) VALUES (?, ?, ?)")
            val code = CodeUtil.getCurriculumCode()
            statement.setString(1,code);
            statement.setString(2, teacherName);
            statement.setString(3,curriculumName);
            rowsAffected = statement.executeUpdate()

            Log.d(TAG, "${statement} ")
            Log.d(TAG, "${rowsAffected} ")

            return rowsAffected

        } catch (e: Exception) {
            // 记录异常并返回
            Log.e("xsc", "Error inserting user: $e")
            return 0
        } finally {
            // 关闭数据库连接
            conn.close()
        }
    }


    const val TAG = "OpenGaussHelper"
}