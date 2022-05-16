package com.kelaniya.android.myapplication.ui.notifications

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.NotificationsListAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.Announcement
import com.kelaniya.android.myapplication.model.MarkAsNotificationAreReadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class NotificationListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseData = requireArguments().getStringArrayList("detailsForGetNotifications") as ArrayList
        val notificationRecyclerView:RecyclerView = view.findViewById(R.id.notifications_recyclerview)
        notificationRecyclerView.layoutManager = LinearLayoutManager(view.context)
        setDataInNotificationList(courseData,notificationRecyclerView)

    }


    private fun setDataInNotificationList(
        courseData: ArrayList<String>,
        notificationRecyclerView: RecyclerView){

        val retrofit = RetrofitBuilder()
        val retrofitBuilder = retrofit.retrofitBuilder


        val retrofitData = retrofitBuilder.getAnnouncements(courseData[0],courseData[1])

        retrofitData!!.enqueue(object : Callback<List<Announcement>> {
            override fun onResponse(call: Call<List<Announcement>>, response: Response<List<Announcement>>) {

                val announcements = response.body()

                try{
                    notificationRecyclerView.adapter = NotificationsListAdapter(announcements!!,findNavController())
                    markAsNotificationsAreRead(courseData[0],courseData[1])

                    }catch (e: Exception){

                    Log.i("error","empty results")

                }

            }

            override fun onFailure(call: Call<List<Announcement>>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })


    }


    fun markAsNotificationsAreRead(courseID: String, academicYear: String) {

        val retrofit = RetrofitBuilder()
        val retrofitBuilder = retrofit.retrofitBuilder


        val retrofitData = retrofitBuilder.markAsNotificationsAreRead(courseID,academicYear)

        retrofitData!!.enqueue(object : Callback<MarkAsNotificationAreReadResponse> {
            override fun onResponse(call: Call<MarkAsNotificationAreReadResponse>, response: Response<MarkAsNotificationAreReadResponse>) {

                val announcements = response.body()

                try{
                   if(announcements!!.value != 0){
                       Log.i("Notification Fragment", "successfully marks notification are read")
                   }
                    else{
                       Log.i("Notification Fragment", "not successfully marks notification are read")
                    }

                }catch (e: Exception){

                    Log.i("error","empty results")

                }

            }

            override fun onFailure(call: Call<MarkAsNotificationAreReadResponse>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })


    }
}