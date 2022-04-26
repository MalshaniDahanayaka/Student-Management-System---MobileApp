package com.kelaniya.android.myapplication.api

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("auth-token" , "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2FzdW5tazk4QGdtYWlsLmNvbSIsImV4cCI6MTY1MDkyNzg2MiwiaWF0IjoxNjUwOTA5ODYyfQ.H1K4xF-vDRsodbkvhh8S4geL5x7WT_TWGaG6XUCrLNZket6JD4eY4cpIIMC0EGoSWICmtfSEWJ2vZhWf6wfdpA")
            .build()
        return chain.proceed(request)
    }


}