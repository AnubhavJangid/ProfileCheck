package com.media.profilecheck.api

import com.media.profilecheck.models.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("/api/")
    suspend fun getUsers(@Query(ApiParam.RESULTS) number : Int) : UserResponse

    //@GET(ApiParam.BASE_URL + "?results=2")
    //suspend fun getUsers() : UserResponse


}