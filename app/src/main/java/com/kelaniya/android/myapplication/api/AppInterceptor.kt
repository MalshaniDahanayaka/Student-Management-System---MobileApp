package com.kelaniya.android.myapplication.api

import android.content.Context
import com.kelaniya.android.myapplication.utils.GetUserDetails
import okhttp3.Interceptor
import okhttp3.Response

open class AppInterceptor : Interceptor {



    private val validTokenValue:String = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2FzdW5tazk4QGdtYWlsLmNvbSIsImV4cCI6MTY1Mjc1OTE3NCwiaWF0IjoxNjUyNjcyNzc0fQ.RkRq_6hoDfdsUJ0_Wcvm4VPw61W2vRLJd6vOJq5WjASZja_PQ3fZbh6TGXbJE6rx69aJELfE2fpMP0l9ghG9Kw"




    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("auth-token" , validTokenValue)
            .build()
        return chain.proceed(request)
    }


}