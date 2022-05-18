package com.kelaniya.android.myapplication.adapter

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.StudentsEnrollSubjects
import com.kelaniya.android.myapplication.model.UnenrollFromCourseRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class EnrollCourseListViewAdapter(private val courses: List<StudentsEnrollSubjects>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderEnrollCourseItems>(){



    //number of Items
    override fun getItemCount(): Int {
        return courses.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderEnrollCourseItems {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.enroll_course_item_view_design,parent,false)
        return CustomViewHolderEnrollCourseItems(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderEnrollCourseItems, position: Int) {

        holder.courseID.text  = courses.elementAt(position).enrolled_course_id
        holder.academicYear.text = courses.elementAt(position).academic_year


    }
}

class CustomViewHolderEnrollCourseItems(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
     val courseID: TextView =    itemView.findViewById(R.id.enrolled_course_id_in_list)
     val academicYear:TextView = itemView.findViewById(R.id.enrolled_course_list_academic_year)
     val unenroll:Button = itemView.findViewById(R.id.course_unenroll_button)


    init{
        view.setOnClickListener{
            var arrayList = ArrayList<String>()
            arrayList.add(courseID.text.toString())
            arrayList.add(academicYear.text.toString())
            findNavController.navigate(R.id.action_nav_gallery_to_course_lecture_notes, Bundle().apply {
                putStringArrayList("courseDetails",arrayList)
            })

        }

        unenroll.setOnClickListener {

            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("Are you sure you want to UnEnroll this Course Module?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->


                    // successfully enrolled
                    val retrofitBuilder = RetrofitBuilder().retrofitBuilder
                    var retrofitData = retrofitBuilder.unenrollFromCourse(courseID.text.toString(),academicYear.text.toString())

                    retrofitData!!.enqueue(object : Callback<UnenrollFromCourseRequest> {
                        override fun onResponse(call: Call<UnenrollFromCourseRequest>, response: Response<UnenrollFromCourseRequest>) {

                            val courses = response.body()
                            try{
                                if (courses != null) {
                                    Toast.makeText(view.context,courses.enrolled_course_id + "successfully unenrolled", Toast.LENGTH_SHORT).show()
                                }
                            }catch (e: Exception){

                                Log.i("error","empty results")
                            }

                        }

                        override fun onFailure(call: Call<UnenrollFromCourseRequest>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                            findNavController.navigate(R.id.action_nav_home_to_connetion_error, Bundle().apply {
                                putString("last_location","homePage")
                            })
                            Log.i("FirstFragment", t.message!!)
                        }
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

}
