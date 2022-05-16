package com.kelaniya.android.myapplication.adapter

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.model.Announcement


class NotificationsListAdapter(private val announcements: List<Announcement>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderForNotifications>(){


    private val numberOfNotifications = announcements.count()
    //number of Items
    override fun getItemCount(): Int {
        return announcements.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderForNotifications {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.notification_list_item_view,parent,false)
        return CustomViewHolderForNotifications(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderForNotifications, position: Int) {

        holder.notificationTitle.text  = announcements.elementAt(numberOfNotifications-1-position).title
        if(announcements.elementAt(numberOfNotifications-1-position).view_state == null){
            holder.notificationTitle.setTextColor(Color.GREEN)
            holder.notificationTitle.setTextSize(1, 25.0F)
        }



    }
}

class CustomViewHolderForNotifications(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val notificationTitle: TextView =    itemView.findViewById(R.id.notification_title)



    init{
        view.setOnClickListener{
            findNavController.navigate(R.id.action_nav_home_to_about_course, Bundle().apply {
//                putString("course",courseID.text.toString())
            })


        }




    }
}