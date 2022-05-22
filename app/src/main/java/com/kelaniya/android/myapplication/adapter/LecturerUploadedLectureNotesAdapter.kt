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
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.DeleteCourseModule
import com.kelaniya.android.myapplication.model.DeleteLectureNote
import com.kelaniya.android.myapplication.model.LecNotes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LecturerUploadedLectureNotesAdapter(private val lecNotes: List<LecNotes>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderLecturerUploadedLectureNotes>(){



    //number of Items
    override fun getItemCount(): Int {
        return lecNotes.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderLecturerUploadedLectureNotes {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.lecture_note_view_design,parent,false)
        return CustomViewHolderLecturerUploadedLectureNotes(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderLecturerUploadedLectureNotes, position: Int) {

        holder.date.text  = lecNotes.elementAt(position).date
        holder.description.text = lecNotes.elementAt(position).description
        holder.fileName.text = "file name :" + lecNotes.elementAt(position).file_name
        holder.fileSize.text= "file size: " + lecNotes.elementAt(position).file_size.toString()+"kb"
        //holder.byteArray.text = lecNotes.elementAt(position).data

    }
}

class CustomViewHolderLecturerUploadedLectureNotes(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view) {
    val date: TextView = itemView.findViewById(R.id.lecturer_uploaded_lecture_note_date)
    val description: TextView = itemView.findViewById(R.id.lecturer_uploaded_lecture_notes_description)
    val fileName: TextView = itemView.findViewById(R.id.lecturer_uploaded_lecture_note_file_name)
    val fileSize: TextView = itemView.findViewById(R.id.lecturer_uploaded_lecture_note_file_size)
    private val download: Button = itemView.findViewById(R.id.lecture_upload_lecture_note_download)
    val byteArray: TextView = itemView.findViewById(R.id.lecture_note_byteArray)


    init {

        download.setOnClickListener {


        }

        view.setOnClickListener {

        }

        view.setOnLongClickListener{

            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("Are you sure you want to delete this letere note?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // successfully delete
                    val lectureNoteFileName: String = fileName.text.toString()

                    val retrofitBuilder = RetrofitBuilder().retrofitBuilder
                    var retrofitData = retrofitBuilder.deleteLectureNote(lectureNoteFileName)


                    retrofitData!!.enqueue(object : Callback<DeleteLectureNote> {
                        override fun onResponse(call: Call<DeleteLectureNote>, response: Response<DeleteLectureNote>) {

                            val deleteLectureNoteDetails = response.body()


                            try{
                                Toast.makeText(view.context, deleteLectureNoteDetails!!.file_name.toString()+" is successfully deleted", Toast.LENGTH_SHORT).show()
                            }catch (e: Exception){

                                Log.i("error","empty results")
                            }

                        }

                        override fun onFailure(call: Call<DeleteLectureNote>, t: Throwable) {
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