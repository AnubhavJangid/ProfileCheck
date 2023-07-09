package com.media.profilecheck.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity
data class Picture(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
)