package com.kelaniya.android.myapplication.api

import com.kelaniya.android.myapplication.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


    @POST("/api/v1/auth/login")
    fun loginUser(@Body user: JwtUserRequest?): Call<JwtResponse>?

    @GET("/api/v1/Courses/{courseID}")
    fun getCourseDetails(@Path("courseID") courseID: String): Call<Courses>?


    @GET("/api/v1/Courses/enrolledCourses")
    fun getEnrollCourseList(): Call<List<StudentsEnrollSubjects>>?

    @GET("/api/v1/Courses/all")
    fun getAllCourses(): Call<List<Courses>>?


    @GET("/api/v1/Courses/department_courses/")
    fun getDepartmentCourseList(): Call<List<Courses>>?

    @POST("/api/v1/student/enroll-subjects")
    fun enrollToCourse(@Body requestEnrollToCourse: EnrollToCourseRequest?): Call<EnrollToCourseRequest>?



    @GET("/api/enrolled_course_detailsRP/{courseID}/{academicYear}")
    fun getCourseLectureNotesRP(@Path("courseID") courseID:String, @Path("academicYear") academicYear:String): Call<List<LecNotes>>?



}