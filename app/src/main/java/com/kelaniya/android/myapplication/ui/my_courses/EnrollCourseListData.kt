package com.kelaniya.android.myapplication.ui.my_courses

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.EnrollCourseListViewAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.StudentsEnrollSubjects
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EnrollCourseListData(val enrollCourseRecyclerview: RecyclerView, val findNavController: NavController): RetrofitBuilder() {

    fun setEnrollCourseListView(){

        var retrofitData = retrofitBuilder.getEnrollCourseList()

        retrofitData!!.enqueue(object : Callback<List<StudentsEnrollSubjects>> {
            override fun onResponse(call: Call<List<StudentsEnrollSubjects>>, response: Response<List<StudentsEnrollSubjects>>) {

                val courses = response.body()


                try{
                    enrollCourseRecyclerview.adapter = EnrollCourseListViewAdapter(courses!!,findNavController)
                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<List<StudentsEnrollSubjects>>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                findNavController.navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
                    putString("last_location","homePage")
                })
                Log.i("FirstFragment", t.message!!)
            }
        })

    }

}