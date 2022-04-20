package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class UsersResult(
    var cell: String?,
    var dob: Dob?,
    var email: String?,
    var gender: String?,
    val name: Name?,
    val phone: String?,
    val picture: Picture?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Dob::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Name::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Picture::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cell)
        parcel.writeParcelable(dob, flags)
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeParcelable(name, flags)
        parcel.writeString(phone)
        parcel.writeParcelable(picture, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsersResult> {
        override fun createFromParcel(parcel: Parcel): UsersResult {
            return UsersResult(parcel)
        }

        override fun newArray(size: Int): Array<UsersResult?> {
            return arrayOfNulls(size)
        }
    }


}