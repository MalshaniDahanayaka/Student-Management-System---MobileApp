package com.kelaniya.android.myapplication.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.MarksAndGradesViewAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentProfileBinding
import com.kelaniya.android.myapplication.model.Student
import com.kelaniya.android.myapplication.model.StudentRecords
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RetrieveProfileData {

    fun getStudentProfileData(binding: FragmentProfileBinding) {

        val retrofitBuilder = RetrofitBuilder().retrofitBuilder
        var retrofitData = retrofitBuilder.getStudentDetails()

        retrofitData!!.enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {

                val student = response.body()

                try{
                    binding.profileDetailsUserEmail.setText(student!!.student_email)
                    binding.userProfileUserId.setText(student.student_id)
                    binding.userProfileFirstName.setText(student.first_name)
                    binding.userProfileLastName.setText(student.last_name)
                    binding.userProfileDepartment.get(0)

                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
//                    putString("last_location","homePage")
//                })
                Log.i("FirstFragment", t.message!!)
            }
        })



    }

    fun getLecturesProfileData(){


    }

}