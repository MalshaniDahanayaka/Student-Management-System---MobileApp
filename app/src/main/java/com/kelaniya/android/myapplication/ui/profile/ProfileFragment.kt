package com.kelaniya.android.myapplication.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelaniya.android.myapplication.databinding.FragmentProfileBinding
import com.kelaniya.android.myapplication.model.Student
import com.kelaniya.android.myapplication.ui.login.RoomDatabase.ResponseUserDetails
import com.kelaniya.android.myapplication.utils.GetUserDetails


class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val responseUserDetails: ResponseUserDetails = GetUserDetails().getData(view.context)
        val userRole = responseUserDetails.userRole.toString()
        if(userRole=="Student"){
            RetrieveProfileData().getStudentProfileData(binding)
        }
        else if(userRole == "Lecturer"){


        }

        binding.userProfileUpdateButton.setOnClickListener {
            if(userRole=="Student"){
                val student:Student = Student(
                                           binding.profileDetailsUserEmail.text.toString(),
                                           binding.userProfileUserId.text.toString(),
                                           binding.userProfileFirstName.text.toString(),
                                           binding.userProfileLastName.text.toString(),
                                           binding.userProfileDepartment.selectedItem.toString(),
                                  "null"
                                           )
                UpdateProfileData().updateStudentsProfileData(student,view.context)
            }
            else if(userRole == "Lecturer"){

            }

        }




    }



}