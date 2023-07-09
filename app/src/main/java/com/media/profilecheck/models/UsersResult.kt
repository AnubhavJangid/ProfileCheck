package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersResult(
    var cell: String?,
    @PrimaryKey(autoGenerate = false)
    var email: String = "",
    var gender: String?,
    val name: Name?,
    val phone: String?,
    val picture: Picture?
    )