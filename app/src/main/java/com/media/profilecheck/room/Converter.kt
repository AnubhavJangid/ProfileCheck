package com.media.profilecheck.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.media.profilecheck.models.Dob
import com.media.profilecheck.models.Name
import com.media.profilecheck.models.Picture
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.repository.UserData
import java.lang.reflect.Type

class Converter {

    @TypeConverter
    fun fromString(value: String?): List<UsersResult> {
        val listType: Type = object : TypeToken<List<UsersResult>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<UsersResult>): String? {
        return Gson().toJson(list)
    }



}