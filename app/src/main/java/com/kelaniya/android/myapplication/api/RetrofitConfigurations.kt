package com.kelaniya.android.myapplication.api



import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.LecNotes
import com.kelaniya.android.myapplication.model.Student
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface UserAPIService {


    @GET("/student/")
    fun getUsers(): Call<List<Student>>



    //@Headers({"Authorization : application/json"})
    @GET("/student/{id}")
    fun getUser(@Path("id") id: Int): Call<Student>


    @GET("/api/v1/allCourses/{courseID}")
    fun getCourseDetails(@Path("courseID") courseID: String):Call<Courses>

    @GET("/api/v1/docs/{subjectName}")
    fun getLectureNotes(@Path("subjectName") subjectName: String):Call<LecNotes>

    companion object {


        private val client = OkHttpClient.Builder().apply {
            addInterceptor(AppInterceptor())

        }.build()


        val API_URL = "http://192.168.142.229:8080"
        fun create(): UserAPIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserAPIService::class.java)
        }
    }
}