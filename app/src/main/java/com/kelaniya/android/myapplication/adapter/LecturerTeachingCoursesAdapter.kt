package com.kelaniya.android.myapplication.adapter

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.DeleteCourseModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LecturerTeachingCoursesAdapter(private val courses: List<Courses>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderLectureHomePage>(){



    //number of Items
    override fun getItemCount(): Int {
        return courses.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderLectureHomePage {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_view_design,parent,false)
        return CustomViewHolderLectureHomePage(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderLectureHomePage, position: Int) {

        holder.courseID.text  = courses.elementAt(position).course_id
        holder.subjectName.text = courses.elementAt(position).course_name
        holder.department.text = courses.elementAt(position).department_name
        holder.academicYear.text= courses.elementAt(position).academic_year


    }
}

class CustomViewHolderLectureHomePage(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val courseID: TextView =    itemView.findViewById(R.id.lecturer_uploaded_lecture_notes_description)
    val subjectName: TextView = itemView.findViewById(R.id.subject_name)
    val department: TextView = itemView.findViewById(R.id.department_name)
    val academicYear: TextView = itemView.findViewById(R.id.notification_title)


    init{
        view.setOnClickListener{
            var arrayList = ArrayList<String>()
            arrayList.add(courseID.text.toString())
            arrayList.add(academicYear.text.toString())
            arrayList.add(subjectName.text.toString())
            findNavController.navigate(R.id.action_lecturer_home_to_lecturer_Uploaded_lecture_notes, Bundle().apply {
                putStringArrayList("courseDetailsData",arrayList)
            })
    }

       view.setOnLongClickListener{

           val builder = AlertDialog.Builder(view.context)
           builder.setMessage("Are you sure you want to Delete this Course Module?")
               .setCancelable(false)
               .setPositiveButton("Yes") { dialog, id ->
                   // successfully delete
                   val deleteCourseModule:DeleteCourseModule = DeleteCourseModule(courseID.text.toString(),academicYear.text.toString())


                   val retrofitBuilder = RetrofitBuilder().retrofitBuilder
                   var retrofitData = retrofitBuilder.removeCourseModule(deleteCourseModule)


                   retrofitData!!.enqueue(object : Callback<DeleteCourseModule> {
                       override fun onResponse(call: Call<DeleteCourseModule>, response: Response<DeleteCourseModule>) {

                           val deletedCourse = response.body()


                           try{
                               Toast.makeText(view.context, deletedCourse!!.course_id+" is successfully deleted", Toast.LENGTH_SHORT).show()
                           }catch (e: Exception){

                               Log.i("error","empty results")
                           }

                       }

                       override fun onFailure(call: Call<DeleteCourseModule>, t: Throwable) {
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




           return@setOnLongClickListener true
       }




    }
}