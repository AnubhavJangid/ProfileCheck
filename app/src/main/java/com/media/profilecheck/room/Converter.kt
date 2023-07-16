package com.media.profilecheck.room

import androidx.room.TypeConverter
import com.media.profilecheck.models.Name
import com.media.profilecheck.models.Picture
import org.json.JSONObject

class Converter {
    @TypeConverter
    fun fromName(name: Name): String {
        return JSONObject().apply {
            put("first", name.first)
            put("last", name.last)
            put("title",name.title)
        }.toString()
    }

    @TypeConverter
    fun toName(stringName : String): Name {
        val json = JSONObject(stringName)
        return Name(json.getString("first"), json.getString("last"), json.getString("title"))
    }

    @TypeConverter
    fun fromPicture(picture: Picture): String {
        return JSONObject().apply {
            put("large", picture.large)
            put("medium", picture.medium)
            put("thumbnail", picture.thumbnail)
        }.toString()
    }

    @TypeConverter
    fun toPicture(stringName : String): Picture {
        val json = JSONObject(stringName)
        return Picture(json.getString("large"),json.getString("medium"),json.getString("thumbnail"))
    }

}