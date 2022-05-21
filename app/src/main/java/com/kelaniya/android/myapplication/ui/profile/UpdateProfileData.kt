package com.kelaniya.android.myapplication.ui.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UpdateProfileData {

    fun updateStudentsProfileData(student: Student, context: Context){
        val retrofitBuilder = RetrofitBuilder().retrofitBuilder
        var retrofitData = retrofitBuilder.updateDetails(student)

        retrofitData!!.enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {

                val student = response.body()

                try{
                  if(student!!.student_email != null){
                      Toast.makeText(context,"Successfully updated",Toast.LENGTH_SHORT).show()
                  }
                }catch (e: Exception){
                    Log.i("error","empty results")
                }

            }
            override fun onFailure(call: Call<Student>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })



    }




    fun updateLecturesProfileData(){

    }
}