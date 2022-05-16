package com.kelaniya.android.myapplication.ui.course_lecture_notes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentCourseLectureNotesBinding
import com.kelaniya.android.myapplication.model.Announcement

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class CourseLectureNotesFragment : Fragment() {


    private var _binding: FragmentCourseLectureNotesBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCourseLectureNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var courseDetails = ArrayList<String>()
        courseDetails = requireArguments().getStringArrayList("courseDetails") as ArrayList<String>
        binding.recyclerViewLectureNotes.layoutManager = LinearLayoutManager(view.context)
        binding.courseIdLectureNotesPage.text = "         " + courseDetails[0]
        val obj:LectureNotesData = LectureNotesData(courseDetails[0],courseDetails[1],binding.recyclerViewLectureNotes,findNavController())
        obj.setDataInPage()
        val notificationButton:Button = binding.notificationButton
        setNewNotificationCount(courseDetails[0],courseDetails[1],notificationButton)


        binding.notificationButton.setOnClickListener {
            var arrayList = ArrayList<String>()
            arrayList.add(courseDetails[0])
            arrayList.add(courseDetails[1])
            findNavController().navigate(R.id.action_course_lecture_notes_to_course_announcement, Bundle().apply {
                putStringArrayList("detailsForGetNotifications",arrayList)
            })
        }



    }

    private fun setNewNotificationCount(
        courseID: String,
        academicYear: String,
        notificationButton: Button
    ){
        val retrofit = RetrofitBuilder()
        val retrofitBuilder = retrofit.retrofitBuilder


        val retrofitData = retrofitBuilder.getAnnouncements(courseID,academicYear)

        retrofitData!!.enqueue(object : Callback<List<Announcement>> {
            override fun onResponse(call: Call<List<Announcement>>, response: Response<List<Announcement>>) {

                val announcement = response.body()
                var count = 0

                try{

                    for (i in 0 until announcement!!.count()){

                        if(announcement!!.elementAt(i).view_state == null) {
                            count++
                        }

                    }

                    notificationButton.text = "$count"
                    notificationButton.setTextColor(Color.GREEN)



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