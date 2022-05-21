package com.kelaniya.android.myapplication.api

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelaniya.android.myapplication.MainActivity
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.utils.GetUserDetails
import java.lang.Exception


class UserJwtToken :Application() {



    private var jwtToken:String? = null


        fun getJtwToken():String{

            try {


                return "Bearer "

            } catch (e: Exception) {
                Log.i("Log i", e.message.toString())
                return "null"

            }

        }



    }
