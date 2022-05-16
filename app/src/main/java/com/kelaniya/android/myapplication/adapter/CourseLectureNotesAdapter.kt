package com.kelaniya.android.myapplication.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.LecNotes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


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
        //holder.byteArray.text = lecNotes.elementAt(position).data

    }
}

class CustomViewHolders(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view) {
    val date: TextView = itemView.findViewById(R.id.lecture_note_date)
    val description: TextView = itemView.findViewById(R.id.enrolled_course_id)
    val fileName: TextView = itemView.findViewById(R.id.lecture_note_file_name)
    val fileSize: TextView = itemView.findViewById(R.id.lecture_note_file_size)
    private val download: Button = itemView.findViewById(R.id.lecture_note_download)
    val byteArray:TextView = itemView.findViewById(R.id.lecture_note_byteArray)


    init {

        download.setOnClickListener {


            var retrofitBuilder:RetrofitBuilder = RetrofitBuilder()
            var fileName = fileName.text.toString()
            println("filenameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+fileName)
            val retrofitData = retrofitBuilder.retrofitBuilder.downloadLectureFile(fileName)

            retrofitData!!.enqueue(object : Callback<LecNotes> {
                override fun onResponse(call: Call<LecNotes>, response: Response<LecNotes>) {

                    val lecNotes = response.body()

                    try{
                        if(lecNotes != null) {
                            println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" )
                            println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + lecNotes.toString())
                            println(lecNotes.data.toString())

                            var file = File("my.pdf")
                            var inputStream = lecNotes.data.toByteArray()


//                            val rawFile: ByteArray = lecNotes.data.toByteArray()
//                            println(lecNotes.data.toString())

                        }
                    }catch (e: Exception){

                        Log.i("error",e.toString())
                    }

                }

                override fun onFailure(call: Call<LecNotes>, t: Throwable) {
//                findNavController.navigate(R.id.action_nav_home_to_connetion_error)
                    findNavController.navigate(R.id.action_course_lecture_notes_to_connetion_error, Bundle().apply {
                        putString("last_location","homePage")
                    })
                    Log.i("FirstFragment", t.message!!)
                }
            })



//            var request = DownloadManager.Request(
//                Uri.parse("http://192.168.142.229:8080/download_lecture_notes/7-Principals-.pdf"))
//                .setTitle("lecture note ")
//                .setDescription("lecture note downloading")
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                .setAllowedOverMetered(true)
//            var dm:DownloadManager = view.context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            dm.enqueue(request)


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