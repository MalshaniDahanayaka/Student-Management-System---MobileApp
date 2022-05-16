package com.kelaniya.android.myapplication.ui.marks_and_grades

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.MarksAndGradesViewAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.StudentRecords
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MarksAndGradesData(val marksAndGradesRecyclerView: RecyclerView, val findNavController: NavController): RetrofitBuilder() {

    fun setStudentRecordsView(){

        var retrofitData = retrofitBuilder.getStudentsMarksAndGrades()

        retrofitData!!.enqueue(object : Callback<List<StudentRecords>> {
            override fun onResponse(call: Call<List<StudentRecords>>, response: Response<List<StudentRecords>>) {

                val studentsRecords = response.body()


                try{
                    marksAndGradesRecyclerView.adapter = MarksAndGradesViewAdapter(studentsRecords!!,findNavController)
                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<List<StudentRecords>>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                findNavController.navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
                    putString("last_location","homePage")
                })
                Log.i("FirstFragment", t.message!!)
            }
        })

    }

}