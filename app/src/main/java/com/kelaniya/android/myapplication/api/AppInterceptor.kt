package com.kelaniya.android.myapplication.api

import android.content.Context
import com.kelaniya.android.myapplication.utils.GetUserDetails
import okhttp3.Interceptor
import okhttp3.Response

open class AppInterceptor : Interceptor {



    private val validTokenValue:String = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2FzdW5tazk4QGdtYWlsLmNvbSIsImV4cCI6MTY1MTk5NzY5MSwiaWF0IjoxNjUxOTExMjkxfQ._eilLqBLs3AROHhQU8xjLGHbELHjc_JprNRUn25t-HG0yqak3d0aV8HuN1K_TlkEpAOUdInlg_bXgXjxohAq3w"




    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("auth-token" , validTokenValue)
            .build()
        return chain.proceed(request)
    }


}