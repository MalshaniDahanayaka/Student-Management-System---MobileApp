package com.kelaniya.android.myapplication.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.kelaniya.android.myapplication.api.UserAPIService
import com.kelaniya.android.myapplication.adapter.Adapter
import com.kelaniya.android.myapplication.api.ApiInterface
import com.kelaniya.android.myapplication.api.AppInterceptor
import com.kelaniya.android.myapplication.databinding.FragmentHomeBinding
import com.kelaniya.android.myapplication.model.Courses
import com.kelaniya.android.myapplication.model.LecNotes
import com.kelaniya.android.myapplication.response_data.HomePageData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val homePageData = HomePageData(binding.recyclerView)
        homePageData.getMyData()

        binding.recyclerView.setBackgroundColor(Color.WHITE)
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
     //   binding.recyclerView.adapter = Adapter()


    }











}





