package com.kelaniya.android.myapplication.response_data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.adapter.Adapter
import com.kelaniya.android.myapplication.api.ApiInterface
import com.kelaniya.android.myapplication.api.AppInterceptor
import com.kelaniya.android.myapplication.databinding.ActivityMainBinding.inflate
import com.kelaniya.android.myapplication.databinding.AppBarMainBinding.inflate
import com.kelaniya.android.myapplication.databinding.FragmentHomeBinding
import com.kelaniya.android.myapplication.model.Courses
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePageData(val recyclerView: RecyclerView){


    fun getMyData(){



        val API_URL = "http://192.168.142.229:8080"


        val client = OkHttpClient.Builder().apply {
            addInterceptor(AppInterceptor())

        }.build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getCourseDetails("seng776647777")

        retrofitData.enqueue(object : Callback<Courses> {
            override fun onResponse(call: Call<Courses>, response: Response<Courses>) {
                val courses = response.body()
                recyclerView.adapter = Adapter(courses!!)



            }

            override fun onFailure(call: Call<Courses?>, t: Throwable) {
                Log.i("FirstFragment", "hukaaapan buuuruwa")
            }
        })




    }

}