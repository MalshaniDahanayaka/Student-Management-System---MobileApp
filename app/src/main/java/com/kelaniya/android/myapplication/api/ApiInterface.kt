package com.kelaniya.android.myapplication.api

import com.kelaniya.android.myapplication.model.Courses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {

        @GET("/api/v1/allCourses/{courseID}")
    fun getCourseDetails(@Path("courseID") courseID: String): Call<Courses>

}