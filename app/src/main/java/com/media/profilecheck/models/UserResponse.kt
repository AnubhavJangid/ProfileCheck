package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class UserResponse(
    val results: List<UsersResult>?
) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(UsersResult))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserResponse> {
        override fun createFromParcel(parcel: Parcel): UserResponse {
            return UserResponse(parcel)
        }

        override fun newArray(size: Int): Array<UserResponse?> {
            return arrayOfNulls(size)
        }
    }
}