package com.kelaniya.android.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.Adapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Courses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomePageData(
    val recyclerView: RecyclerView,
    val findNavController: NavController
): RetrofitBuilder() {


    fun getHomePageData(request:String){

        var retrofitData = retrofitBuilder.getDepartmentCourseList()
        if(request == "department"){
            retrofitData = retrofitBuilder.getDepartmentCourseList()
        }else if (request == "all"){
            retrofitData = retrofitBuilder.getAllCourses()
        }



            retrofitData!!.enqueue(object : Callback<List<Courses>> {
            override fun onResponse(call: Call<List<Courses>>, response: Response<List<Courses>>) {

                val courses = response.body()


                try{
                    recyclerView.adapter = Adapter(courses!!,findNavController)
                }catch (e:Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<List<Courses>>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                findNavController.navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
                    putString("last_location","homePage")
                })
                Log.i("FirstFragment", t.message!!)
            }
        })

    }




}