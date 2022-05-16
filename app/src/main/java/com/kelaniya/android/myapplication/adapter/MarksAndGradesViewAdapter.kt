package com.kelaniya.android.myapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.model.StudentRecords
import com.kelaniya.android.myapplication.model.StudentsEnrollSubjects

class MarksAndGradesViewAdapter(private val marksAndGrades: List<StudentRecords>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderStudentRecords>(){



    //number of Items
    override fun getItemCount(): Int {
        return marksAndGrades.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderStudentRecords {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.marks_and_grades_row_item_view,parent,false)
        return CustomViewHolderStudentRecords(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderStudentRecords, position: Int) {

        holder.courseID.text  = "  " + marksAndGrades.elementAt(position).course_id
        holder.marks.text = marksAndGrades.elementAt(position).score.toString()
        holder.grades.text = marksAndGrades.elementAt(position).grade


    }
}

class CustomViewHolderStudentRecords(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val courseID: TextView =    itemView.findViewById(R.id.marks_table_course_id)
    val marks: TextView = itemView.findViewById(R.id.marks_table_course_marks)
    val grades:TextView = itemView.findViewById(R.id.marks_table_course_grade)

}