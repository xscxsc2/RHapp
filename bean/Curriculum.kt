package com.arcsoft.arcfacedemo.thisapp.bean

data class Curriculum(

    val code: String?,
    val ownerName: String?,
    val curriculumName: String?

){
    constructor(curriculumName: String) : this("", "", curriculumName) {
        // 这里可以放置一些初始化代码，如果有必要的话
    }

}