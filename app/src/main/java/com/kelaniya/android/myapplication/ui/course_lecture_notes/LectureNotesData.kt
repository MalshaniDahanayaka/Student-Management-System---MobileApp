package com.kelaniya.android.myapplication.ui.course_lecture_notes

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.CourseLectureNotesAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.GetLectureNotesRequest
import com.kelaniya.android.myapplication.model.LecNotes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LectureNotesData(
    private val courseID: String,
    private val academicYear:String,
    val recyclerView: RecyclerView,
    val findNavController: NavController) :RetrofitBuilder(){

    fun setDataInPage(){


        val retrofitData = retrofitBuilder.getCourseLectureNotesRP(courseID,academicYear)

        retrofitData!!.enqueue(object : Callback<List<LecNotes>> {
            override fun onResponse(call: Call<List<LecNotes>>, response: Response<List<LecNotes>>) {

                val lecNotes = response.body()
                println("dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+lecNotes.toString())

                try{
                    recyclerView.adapter = CourseLectureNotesAdapter(lecNotes!!,findNavController)
                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<List<LecNotes>>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                findNavController.navigate(R.id.action_course_lecture_notes_to_connetion_error, Bundle().apply {
                    putString("last_location","homePage")
                })
                Log.i("FirstFragment", t.message!!)
            }
        })
    }
}