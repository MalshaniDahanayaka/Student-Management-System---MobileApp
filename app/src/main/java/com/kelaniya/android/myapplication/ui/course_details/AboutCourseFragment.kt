package com.kelaniya.android.myapplication.ui.course_details

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentAboutCourseBinding
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.EnrollToCourseRequest
import com.kelaniya.android.myapplication.utils.GetUserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class AboutCourseFragment : Fragment() {


    private var _binding: FragmentAboutCourseBinding? = null
    val courseData = ArrayList<String>()

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAboutCourseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var courseId:String? = null
        courseId = requireArguments().getString("course") as String




        val retrofitBuilderObj = RetrofitBuilder()
        val retrofitBuilder = retrofitBuilderObj.retrofitBuilder
        var retrofitData = retrofitBuilder.getCourseDetails(courseId)

        retrofitData!!.enqueue(object : Callback<Courses> {
            override fun onResponse(call: Call<Courses>, response: Response<Courses>) {

                val course = response.body()


                try{

                    binding.coursesId.text = course!!.course_id
                    binding.coursesName.text = course.course_name
                    binding.departmentsName.text = course.department_name
                    binding.lecturersEmail.text = course.lecturer
                    binding.levelValue.text = course.level
                    binding.academicYearValue.text = course.academic_year
                    binding.semesterValue.text = course.semester
                    binding.descriptionValue.text = course.course_description


                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<Courses>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                  findNavController().navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
                    putString("last_location","homePage")
                })
                Log.i("FirstFragment", t.message!!)
            }
        })


       binding.buttonBack.setOnClickListener {
           findNavController().navigate(R.id.action_about_course_to_nav_home)
       }

        binding.buttonEnroll.setOnClickListener {

            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("Are you sure you want to Enroll this Course Module?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // successfully enrolled
                    enrollToCourse(binding.coursesId.text as String,binding.academicYearValue.text as String,view)

                    val courseID:String = binding.coursesId.text.toString()
                    val academicYear:String = binding.academicYearValue.text.toString()
                    courseData.add(courseID)
                    courseData.add(academicYear)
                    Toast.makeText(view.context,courseID, Toast.LENGTH_SHORT).show()


                    findNavController().navigate(R.id.action_about_course_to_course_lecture_notes, Bundle().apply {
                        putStringArrayList("courseDetails",courseData)
                    })

                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        }






    }

    private fun enrollToCourse(courseId: String, academicYear: String, view: View) {
        val user = GetUserDetails().getData(view.context)
        val userEmail:String = user.userEmail.toString()
        val enrollToCourseRequest : EnrollToCourseRequest = EnrollToCourseRequest(userEmail,courseId,academicYear)
        val retrofitBuilderObj = RetrofitBuilder()
        val retrofitBuilder = retrofitBuilderObj.retrofitBuilder
        var retrofitData = retrofitBuilder.enrollToCourse(enrollToCourseRequest)

        retrofitData!!.enqueue(object : Callback<EnrollToCourseRequest> {
            override fun onResponse(call: Call<EnrollToCourseRequest>, response: Response<EnrollToCourseRequest>) {

                val course = response.body()


                try{
                    Toast.makeText(view.context,"successfully enroll to ${course!!.enrolled_course_id}", Toast.LENGTH_SHORT).show()


                }catch (e: Exception){

                    Log.i("error","empty results")
                }

            }

            override fun onFailure(call: Call<EnrollToCourseRequest>, t: Throwable) {

                Log.i("FirstFragment", t.message!!)
            }
        })

    }



    }