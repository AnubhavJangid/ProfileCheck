package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity


data class Name(
    val first: String?,
    val last: String?,
    val title: String?
)