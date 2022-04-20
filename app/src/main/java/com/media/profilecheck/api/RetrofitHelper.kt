package com.media.profilecheck.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiParam.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getRetrofitClient())
            .build()
    }


    private fun getRetrofitClient(): OkHttpClient {
        val logging = getLoggingInstance()

        return OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(logging).build()
    }

    private fun getLoggingInstance(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}