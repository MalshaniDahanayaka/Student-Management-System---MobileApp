package com.kelaniya.android.myapplication.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //default data (department courses)
        val homePageData = HomePageData(binding.recyclerView,findNavController())
        homePageData.getHomePageData("all")
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)


        binding.departmentCourses.setOnClickListener{
            binding.departmentCourses.setTextColor(Color.GREEN)
            binding.allCourses.setTextColor(Color.WHITE)
            val homePageData = HomePageData(binding.recyclerView,findNavController())
            homePageData.getHomePageData("department")

            //  binding.recyclerView.setBackgroundColor()
            binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
            //   binding.recyclerView.adapter = Adapter()


        }

        binding.allCourses.setOnClickListener {
            binding.allCourses.setTextColor(Color.GREEN)
            binding.departmentCourses.setTextColor(Color.WHITE)
            val homePageData = HomePageData(binding.recyclerView,findNavController())
            homePageData.getHomePageData("all")

            //  binding.recyclerView.setBackgroundColor()
            binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
            //   binding.recyclerView.adapter = Adapter()
        }



    }











}





