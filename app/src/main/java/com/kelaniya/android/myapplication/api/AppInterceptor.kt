package com.kelaniya.android.myapplication.api


import com.kelaniya.android.myapplication.utils.GetUserDetails
import com.kelaniya.android.myapplication.utils.MyApp
import okhttp3.Interceptor
import okhttp3.Response

open class AppInterceptor : Interceptor {



    val jwtToken = GetUserDetails().getData(MyApp.instance!!).jwtToken
    private val validTokenValue:String = "Bearer $jwtToken"


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("auth-token" , validTokenValue)
            .build()
        return chain.proceed(request)
    }


}


