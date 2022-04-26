package com.kelaniya.android.myapplication.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.LecNotes
import com.kelaniya.android.myapplication.ui.home.HomeFragment
import java.util.ArrayList


class Adapter(val course: Courses): RecyclerView.Adapter<CustomViewHolder>(){



    //number of Items
    override fun getItemCount(): Int {
        return 18
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_view_design,parent,false)
        return CustomViewHolder(cellForRow)
    }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            holder.latitude.text  = course.course_id
            holder.longitude.text = course.course_name
            holder.name.text = course.lecturer


    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val name: TextView =    itemView.findViewById(R.id.name)
    val latitude: TextView = itemView.findViewById(R.id.latitude)
    val longitude: TextView = itemView.findViewById(R.id.longitude)


    init{
        view.setOnClickListener{
//         val intent = Intent(view.context, MapsActivity::class.java)
//            intent.putExtra("locationName",name.text)
//            intent.putExtra("latitude",latitude.text)
//            intent.putExtra("longitude",longitude.text)
//
//
//            view.context.startActivity(intent)

        }




    }
}