package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable

data class Dob(
    var age: Int,
    var date: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dob> {
        override fun createFromParcel(parcel: Parcel): Dob {
            return Dob(parcel)
        }

        override fun newArray(size: Int): Array<Dob?> {
            return arrayOfNulls(size)
        }
    }

}