package com.kelaniya.android.myapplication.ui.my_courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.databinding.FragmentHomeBinding
import com.kelaniya.android.myapplication.databinding.FragmentMyCoursesBinding

class MyCoursesFragment : Fragment() {

    private var _binding: FragmentMyCoursesBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyCoursesBinding.inflate(inflater, container, false)
        return binding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enrollCourseRecyclerview.layoutManager = LinearLayoutManager(view.context)
        EnrollCourseListData(binding.enrollCourseRecyclerview,findNavController()).setEnrollCourseListView()





    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}