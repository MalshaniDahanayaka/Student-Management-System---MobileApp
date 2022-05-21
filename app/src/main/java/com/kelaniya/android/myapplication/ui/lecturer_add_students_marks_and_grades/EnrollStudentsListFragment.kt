package com.kelaniya.android.myapplication.ui.lecturer_add_students_marks_and_grades

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.adapter.EnrollStudentsMarkAndGradesViewAdapter
import com.kelaniya.android.myapplication.adapter.LecturerTeachingCoursesAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentEnrollStudentsListBinding
import com.kelaniya.android.myapplication.model.EnrollStudentsMarksAndGradesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class EnrollStudentsListFragment : Fragment() {

    private var _binding: FragmentEnrollStudentsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEnrollStudentsListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var courseID = requireArguments().getString("courseID") as String
        binding.textView2.text = " Marks And Grades ($courseID)"
        setEnrollStudentsCourseList(courseID,binding.lectureEnrollCourseStudentsRecyclerView,findNavController())
        binding.lectureEnrollCourseStudentsRecyclerView.layoutManager = LinearLayoutManager(view.context)

    }



    private fun setEnrollStudentsCourseList(
        courseID: String,
        lectureEnrollCourseStudentsRecyclerView: RecyclerView,
        findNavController: NavController
    ){
        val retrofitBuilder = RetrofitBuilder().retrofitBuilder
        var retrofitData = retrofitBuilder.getSelectedCourseStudentsMarksAndGrades(courseID)


        retrofitData!!.enqueue(object : Callback<List<EnrollStudentsMarksAndGradesResponse>> {
            override fun onResponse(call: Call<List<EnrollStudentsMarksAndGradesResponse>>, response: Response<List<EnrollStudentsMarksAndGradesResponse>>) {

                val records = response.body()



                try{
                    lectureEnrollCourseStudentsRecyclerView.adapter = EnrollStudentsMarkAndGradesViewAdapter(records!!,findNavController)
                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<List<EnrollStudentsMarksAndGradesResponse>>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })

    }



}