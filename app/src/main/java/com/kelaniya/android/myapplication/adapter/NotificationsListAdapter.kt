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
import org.w3c.dom.Text


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
        holder.notificationBody.text = announcements.elementAt(numberOfNotifications-1-position).body
        holder.lecturerEmail.text = announcements.elementAt(numberOfNotifications-1-position).lecturer_email
        holder.courseIDValue.text = announcements.elementAt(numberOfNotifications-1-position).category




    }
}

class CustomViewHolderForNotifications(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val notificationTitle: TextView =    itemView.findViewById(R.id.notification_title)
    val notificationBody:TextView = itemView.findViewById(R.id.send_notification_body)
    val lecturerEmail:TextView = itemView.findViewById(R.id.send_lecturer_id)
    val courseIDValue:TextView = itemView.findViewById(R.id.send_course_id)



    init{

        view.setOnClickListener{

            var announcementDetails = ArrayList<String>()
            announcementDetails.add(notificationTitle.text.toString())
            announcementDetails.add(notificationBody.text.toString())
            announcementDetails.add(lecturerEmail.text.toString())
            announcementDetails.add(courseIDValue.text.toString())


            findNavController.navigate(R.id.action_course_announcement_to_course_announcement_full_body, Bundle().apply {
                putStringArrayList("announcementDetails",announcementDetails)
            })


        }




    }
}