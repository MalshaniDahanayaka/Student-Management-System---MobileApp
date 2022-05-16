package com.kelaniya.android.myapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.StudentsEnrollSubjects


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


    init{
        view.setOnClickListener{
            var arrayList = ArrayList<String>()
            arrayList.add(courseID.text.toString())
            arrayList.add(academicYear.text.toString())
            findNavController.navigate(R.id.action_nav_gallery_to_course_lecture_notes, Bundle().apply {
                putStringArrayList("courseDetails",arrayList)
            })

        }


        }




    }
