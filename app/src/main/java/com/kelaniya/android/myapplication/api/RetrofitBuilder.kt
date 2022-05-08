package com.kelaniya.android.myapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitBuilder :ApiURL(){

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(AppInterceptor())

    }.build()

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)!!
}