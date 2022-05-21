package com.kelaniya.android.myapplication.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Announcement
import com.kelaniya.android.myapplication.model.DeleteAnnouncementRequest
import com.kelaniya.android.myapplication.model.DeleteAnnouncementResponse
import com.kelaniya.android.myapplication.model.DeleteCourseModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LecturerNotificationListAdapter(private val announcements: List<Announcement>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderForLecturerNotifications>(){


    private val numberOfNotifications = announcements.count()
    //number of Items
    override fun getItemCount(): Int {
        return announcements.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderForLecturerNotifications {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.notification_list_item_view,parent,false)
        return CustomViewHolderForLecturerNotifications(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderForLecturerNotifications, position: Int) {

        holder.notificationTitle.text  = announcements.elementAt(numberOfNotifications-1-position).title
        holder.notificationBody.text = announcements.elementAt(numberOfNotifications-1-position).body
        holder.lecturerEmail.text = announcements.elementAt(numberOfNotifications-1-position).lecturer_email
        holder.courseIDValue.text = announcements.elementAt(numberOfNotifications-1-position).category




    }
}

class CustomViewHolderForLecturerNotifications(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val notificationTitle: TextView =    itemView.findViewById(R.id.notification_title)
    val notificationBody: TextView = itemView.findViewById(R.id.send_notification_body)
    val lecturerEmail: TextView = itemView.findViewById(R.id.send_lecturer_id)
    val courseIDValue: TextView = itemView.findViewById(R.id.send_course_id)



    init{

        view.setOnClickListener{

            var announcementDetails = ArrayList<String>()
            announcementDetails.add(notificationTitle.text.toString())
            announcementDetails.add(notificationBody.text.toString())
            announcementDetails.add(lecturerEmail.text.toString())
            announcementDetails.add(courseIDValue.text.toString())


            findNavController.navigate(R.id.action_lecturer_add_announcements_list_to_course_announcement_full_body, Bundle().apply {
                putStringArrayList("announcementDetails",announcementDetails)
            })

        }


        view.setOnLongClickListener{

            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("Are you sure you want to Delete this announcement?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // successfully delete
                    val deleteAnnouncementRequest: DeleteAnnouncementRequest = DeleteAnnouncementRequest(notificationTitle.text.toString(),courseIDValue.text.toString())


                    val retrofitBuilder = RetrofitBuilder().retrofitBuilder
                    var retrofitData = retrofitBuilder.deleteAnnouncement(deleteAnnouncementRequest)


                    retrofitData!!.enqueue(object : Callback<DeleteAnnouncementResponse> {
                        override fun onResponse(call: Call<DeleteAnnouncementResponse>, response: Response<DeleteAnnouncementResponse>) {

                            val deleteAnnouncement = response.body()


                            try{
                                if(deleteAnnouncement != null){
                                Toast.makeText(view.context, "Announcement is successfully deleted", Toast.LENGTH_SHORT).show()
                                }
                            }catch (e: Exception){

                                Log.i("error","empty results")
                            }

                        }

                        override fun onFailure(call: Call<DeleteAnnouncementResponse>, t: Throwable) {
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