package com.kelaniya.android.myapplication.ui.lecturer_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentLecturerHomeBinding

import com.kelaniya.android.myapplication.ui.home.HomePageData


class LecturerHomeFragment : Fragment() {


    private var _binding: FragmentLecturerHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLecturerHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homePageData = LecturerHomePageData(binding.lecturerHomeRecyclerView,findNavController())
        homePageData.getHomePageData()
        binding.lecturerHomeRecyclerView.layoutManager = LinearLayoutManager(view.context)


        binding.lecturerHomeButtonAddNewCourse.setOnClickListener {
            findNavController().navigate(R.id.action_lecturer_home_to_lecturer_add_new_course_module)

        }








    }


}