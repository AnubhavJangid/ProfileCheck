package com.media.profilecheck.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.media.profilecheck.models.Dob
import com.media.profilecheck.models.Name
import com.media.profilecheck.models.Picture
import com.media.profilecheck.models.UsersResult

@Entity(tableName = "userData")
data class UserData(
    val results: List<UsersResult>?
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}