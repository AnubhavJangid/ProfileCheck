package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable

data class Name(
    val first: String?,
    val last: String?,
    val title: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        first = parcel.readString(),
        last = parcel.readString(),
        title = parcel.readString()
    )

    companion object CREATOR : Parcelable.Creator<Name> {
        override fun createFromParcel(parcel: Parcel): Name {
            return Name(parcel)
        }

        override fun newArray(size: Int): Array<Name?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(first)
        parcel.writeString(last)
        parcel.writeString(title)
    }
}