package com.arcsoft.arcfacedemo.thisapp.opengauss;

import android.util.Log;

import com.arcsoft.arcfacedemo.facedb.entity.FaceEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class OpenGaussJavaHelper {
    private static final String URL = "jdbc:postgresql://192.168.17.239:26000/postgres";
    private static final String DATABASE_NAME = "xsc";
    private static final String DATABASE_PASSWORD = "xsc20240401.";
    private static final String TAG = "OpenGaussJavaHelper";

    public static void registerUser(FaceEntity faceEntity) {
        Connection conn = null;
        int rowsAffected = 0;
        String error = null;

        Log.d(TAG, "registerUser");
        try {
            byte[] byteArray = new byte[2];
            // 随机填充字节数组
            for (int i = 0; i < byteArray.length; i++) {
                byteArray[i] = (byte) (new Random().nextInt(256));
            }
            conn = DriverManager.getConnection(URL, DATABASE_NAME, DATABASE_PASSWORD);
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO face_table (sno_id, user_name, password, image_path, feature_data, register_time) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setLong(1, 123456);
            statement.setString(2, "faceEntity.getUserName()");
            statement.setString(3, "faceEntity.getPassword()");
            statement.setString(4, "faceEntity.getImagePath()");
            statement.setBytes(5, byteArray);
            statement.setLong(6, 75574);
            rowsAffected = statement.executeUpdate();

            Log.d(TAG, "rowsAffected: " + rowsAffected);
        } catch (Exception e) {
            error = "Error inserting user: " + e.toString();
            e.printStackTrace();
            Log.e(TAG, error);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                error = "Error closing database connection: " + e.getMessage();
                Log.e(TAG, error);
            }
        }
    }

    public  static Connection getConnect(String username, String passwd)
    {
        Log.d(TAG, "getConnect: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        //驱动类。
        String driver = "org.postgresql.Driver";
        //数据库连接描述符。
        String sourceURL = "jdbc:postgresql://192.168.17.239:26000/postgres";
        Connection conn = null;

        try
        {
            //加载驱动。
            Class.forName(driver);
        }
        catch( Exception e )
        {
            e.printStackTrace();
            return null;
        }

        try
        {
            //创建连接。
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            System.out.println("2100301433徐率朝连接成功！\n\n");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return conn;
    };



}

