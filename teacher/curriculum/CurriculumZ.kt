package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.os.Parcel
import android.os.Parcelable


data class CurriculumZ(

    val code: String?,
    val ownerName: String?,
    val curriculumName: String?,
    val ischeck: Int

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(ownerName)
        parcel.writeString(curriculumName)
        parcel.writeInt(ischeck)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurriculumZ> {
        override fun createFromParcel(parcel: Parcel): CurriculumZ {
            return CurriculumZ(parcel)
        }

        override fun newArray(size: Int): Array<CurriculumZ?> {
            return arrayOfNulls(size)
        }
    }
}

