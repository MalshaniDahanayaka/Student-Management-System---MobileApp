package com.kelaniya.android.myapplication.api


import okhttp3.Interceptor
import okhttp3.Response

open class AppInterceptor : Interceptor {

//    val context:
//    private val instance = AppDatabase.getAppDatabase(context)
//    private val userDao:UserDao = instance!!.userDao()
//    val userEntity:UserEntity = userDao.getUser()
    private val validTokenValue:String = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2FzdW5tazk4MkBnbWFpbC5jb20iLCJleHAiOjE2NTI5Mjg4NjIsImlhdCI6MTY1Mjg0MjQ2Mn0.BiMBh0v99SFoCx-gbL1KwrDQa0vLzH4wQce-wcuOhQuPHUN9fZhEWtwfqPNKBvLAxUrlmKZm0weYcfu9C1Zb1Q"


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("auth-token" , validTokenValue)
            .build()
        return chain.proceed(request)
    }


}


