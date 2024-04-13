package com.arcsoft.arcfacedemo.thisapp.bean

import android.os.Parcel
import android.os.Parcelable

data class Curriculum(

    val code: String?,
    val ownerName: String?,
    val curriculumName: String?

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor(curriculumName: String) : this("", "", curriculumName) {
        // 这里可以放置一些初始化代码，如果有必要的话
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(ownerName)
        parcel.writeString(curriculumName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Curriculum> {
        override fun createFromParcel(parcel: Parcel): Curriculum {
            return Curriculum(parcel)
        }

        override fun newArray(size: Int): Array<Curriculum?> {
            return arrayOfNulls(size)
        }
    }

}