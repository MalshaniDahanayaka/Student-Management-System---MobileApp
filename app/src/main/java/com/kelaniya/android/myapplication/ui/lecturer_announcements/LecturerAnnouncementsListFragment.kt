package com.kelaniya.android.myapplication.ui.lecturer_announcements

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
import com.kelaniya.android.myapplication.adapter.LecturerNotificationListAdapter
import com.kelaniya.android.myapplication.adapter.NotificationsListAdapter
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentLecturerAnnouncementsListBinding
import com.kelaniya.android.myapplication.databinding.FragmentLecturerHomeBinding
import com.kelaniya.android.myapplication.model.Announcement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class LecturerAnnouncementsListFragment : Fragment() {
    private var _binding: FragmentLecturerAnnouncementsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLecturerAnnouncementsListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var courseDetails = ArrayList<String>()
        courseDetails = requireArguments().getStringArrayList("courseData") as ArrayList<String>

        binding.lecturerTeachingSubjectsNotificationRecyclerView.layoutManager = LinearLayoutManager(view.context)
        setDataInNotificationList(courseDetails[0],courseDetails[1],binding.lecturerTeachingSubjectsNotificationRecyclerView)

        binding.lecturerAddAnnouncementAddNewButton.setOnClickListener {
            findNavController().navigate(R.id.action_lecturer_add_announcements_list_to_lecturer_add_new_announcement, Bundle().apply {
                putString("category",courseDetails[0])
            })
        }

    }





    private fun setDataInNotificationList(
        courseID:String,
        academicYear:String,
        notificationRecyclerView: RecyclerView
    ){

        val retrofit = RetrofitBuilder()
        val retrofitBuilder = retrofit.retrofitBuilder


        val retrofitData = retrofitBuilder.getAnnouncements(courseID,academicYear)

        retrofitData!!.enqueue(object : Callback<List<Announcement>> {
            override fun onResponse(call: Call<List<Announcement>>, response: Response<List<Announcement>>) {

                val announcements = response.body()

                try{
                    notificationRecyclerView.adapter = LecturerNotificationListAdapter(announcements!!,findNavController())

                }catch (e: Exception){

                    Log.i("error","empty results")

                }

            }

            override fun onFailure(call: Call<List<Announcement>>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })


    }


}