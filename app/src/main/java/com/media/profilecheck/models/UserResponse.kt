package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class UserResponse(
    val results: List<UsersResult>?
)