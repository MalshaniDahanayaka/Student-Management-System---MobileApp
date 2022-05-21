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


class Adapter(private val courses: List<Courses>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolder>(){



    //number of Items
    override fun getItemCount(): Int {
        return courses.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_view_design,parent,false)
        return CustomViewHolder(cellForRow,findNavController)
    }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            holder.courseID.text  = courses.elementAt(position).course_id
            holder.subjectName.text = courses.elementAt(position).course_name
            holder.department.text = courses.elementAt(position).department_name
            holder.level.text= courses.elementAt(position).level


    }
}

class CustomViewHolder(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val courseID: TextView =    itemView.findViewById(R.id.lecturer_uploaded_lecture_notes_description)
    val subjectName: TextView = itemView.findViewById(R.id.subject_name)
    val department: TextView = itemView.findViewById(R.id.department_name)
    val level: TextView = itemView.findViewById(R.id.notification_title)


    init{
        view.setOnClickListener{
            findNavController.navigate(R.id.action_nav_home_to_about_course, Bundle().apply {
                putString("course",courseID.text.toString())
            })




        }




    }
}