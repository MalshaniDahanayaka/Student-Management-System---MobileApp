package com.kelaniya.android.myapplication.ui.add_new_course

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.CreateNewCourseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SaveNewCourseModule(val course: Courses, val view: View) : RetrofitBuilder() {

    fun saveDataAboutCourse() {


        val retrofitData = retrofitBuilder.addNewCourseModule(course)

        retrofitData!!.enqueue(object : Callback<Courses> {
            override fun onResponse(
                call: Call<Courses>,
                response: Response<Courses>
            ) {

                val courseDetails = response.body()

                try {

                    Toast.makeText(view.context,"${courseDetails!!.course_name} successfully added", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {

                    Log.i("error", "empty results")
                }

            }
            override fun onFailure(call: Call<Courses>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })
    }




}
