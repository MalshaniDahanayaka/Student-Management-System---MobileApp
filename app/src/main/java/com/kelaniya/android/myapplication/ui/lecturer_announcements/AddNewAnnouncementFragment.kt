package com.kelaniya.android.myapplication.ui.lecturer_announcements

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentAddNewAnnouncementBinding
import com.kelaniya.android.myapplication.databinding.FragmentLecturerHomeBinding
import com.kelaniya.android.myapplication.model.Announcement
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.CreateNewAnnouncementRequest
import com.kelaniya.android.myapplication.utils.GetUserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddNewAnnouncementFragment : Fragment() {
    private var _binding: FragmentAddNewAnnouncementBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewAnnouncementBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lecturerEmail = GetUserDetails().getData(view.context).userEmail.toString()
        var category = requireArguments().getString("category") as String

        binding.addNewAnnouncementCategory.text = category
        binding.addNewAnnouncementLecturerEmail.text = lecturerEmail

        binding.addNewAnnouncementPostButton.setOnClickListener {

            var title = binding.addNewAnnouncementTitle.text.toString()
            var body = binding.addNewAnnouncementBody.text.toString()
            var categoryType = category
            var academicYear = binding.addNewAnnouncementAcademicYear.selectedItem.toString()
            val createNewAnnouncementRequest:CreateNewAnnouncementRequest = CreateNewAnnouncementRequest(title,body,categoryType,academicYear)
            saveData(createNewAnnouncementRequest,view)



        }

    }


    fun saveData(createNewAnnouncementRequest: CreateNewAnnouncementRequest,view: View){

        val retrofitBuilder = RetrofitBuilder().retrofitBuilder
        val retrofitData = retrofitBuilder.makeAnnouncement(createNewAnnouncementRequest)

        retrofitData!!.enqueue(object : Callback<Announcement> {
            override fun onResponse(
                call: Call<Announcement>,
                response: Response<Announcement>
            ) {

                val announcement = response.body()

                try {

                    Toast.makeText(view.context,"announcement successfully created", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {

                    Log.i("error", "empty results")
                }

            }
            override fun onFailure(call: Call<Announcement>, t: Throwable) {
                Log.i("FirstFragment", t.message!!)
            }
        })
    }

}