package com.arcsoft.arcfacedemo.thisapp.util

import com.arcsoft.arcfacedemo.thisapp.category.Category


/**
 * 数据工具类
 */
object DataUtil {
    val categoriesTeacher: List<Category> = listOf(
        Category.create(
            "1", "课程"
        ), Category.create(
            "2", "导出"
        ),
//        Category.create(
//            "3", "每日必看"
//        ), Category.create(
//            "4", "视频"
//        ), Category.create(
//            "5", "小说"
//        ), Category.create(
//            "6", "问答"
//        ), Category.create(
//            "7", "音乐"
//        ), Category.create(
//            "8", "发现"
//        )
    )

    val categoriesStudent: List<Category> = listOf(
        Category.create(
            "1", "当前课程"
        ), Category.create(
            "2", "我的课程"
        ),
//        Category.create(
//            "3", "每日必看"
//        ), Category.create(
//            "4", "视频"
//        ), Category.create(
//            "5", "小说"
//        ), Category.create(
//            "6", "问答"
//        ), Category.create(
//            "7", "音乐"
//        ), Category.create(
//            "8", "发现"
//        )
    )

}