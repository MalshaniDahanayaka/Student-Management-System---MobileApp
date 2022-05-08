package com.kelaniya.android.myapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.model.LecNotes

class CourseLectureNotesAdapter(private val lecNotes: List<LecNotes>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolders>(){



    //number of Items
    override fun getItemCount(): Int {
        return lecNotes.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolders {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.lecture_note_view_design,parent,false)
        return CustomViewHolders(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolders, position: Int) {

        holder.date.text  = lecNotes.elementAt(position).date
        holder.description.text = lecNotes.elementAt(position).description
        holder.fileName.text = lecNotes.elementAt(position).file_name
        holder.fileSize.text= lecNotes.elementAt(position).file_size.toString()



    }
}

class CustomViewHolders(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view) {
    val date: TextView = itemView.findViewById(R.id.lecture_note_date)
    val description: TextView = itemView.findViewById(R.id.description_of_lectuer_note)
    val fileName: TextView = itemView.findViewById(R.id.lecture_note_file_name)
    val fileSize: TextView = itemView.findViewById(R.id.lecture_note_file_size)
    private val download: Button = itemView.findViewById(R.id.lecture_note_download)


    init {

        download.setOnClickListener {


        }

        view.setOnClickListener {
//            findNavController.navigate(R.id.action_nav_home_to_about_course, Bundle().apply {
//                putString("course", courseID.text.toString())
//            })


//            val intent = Intent(view.context, HomeFragment::class.java)
//            intent.putExtra("course_id",courseID.text)
//            view.context.startActivity(intent)

        }


    }

}