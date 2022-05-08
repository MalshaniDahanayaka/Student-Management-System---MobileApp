package com.kelaniya.android.myapplication.utils

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.room.Room
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.*
import com.kelaniya.android.myapplication.ui.login.RoomDatabase.AppDatabase
import com.kelaniya.android.myapplication.ui.login.RoomDatabase.UserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Authentication(
    val user: JwtUserRequest,
    val context: Context,
    val findNavController: NavController
) : RetrofitBuilder() {


    private val appContext:Context = context.getApplicationContext()
    fun authenticate():String{

            val retrofitData = retrofitBuilder.loginUser(user)
            var state:String? = null
            var userEmail:String
            var role:Set<Role>
            var jwtToken:String



        retrofitData!!.enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {

                    val userDetails = response.body()
                    var userRole:String = ""



                    if (userDetails!!.responseUser.toString() == null){
                        println(userDetails)
                        Toast.makeText(context,"Invalid user name or password",Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(context, "Well Come " + userDetails.responseUser.username,Toast.LENGTH_SHORT).show()
                        userEmail = userDetails.responseUser.username
                        userRole = userDetails.responseUser.role.elementAt(0).roleName
                        jwtToken = userDetails.jwtToken

                        if(saveInDatabase(userEmail,userRole,jwtToken) == "done"){
                            findNavController.navigate(R.id.action_login_to_nav_home)
                        }

                    }

                }

                override fun onFailure(call: Call<JwtResponse?>, t: Throwable) {
                    findNavController.navigate(R.id.action_login_to_connetion_error, Bundle().apply {
                        putString("last_location","loginPage")
                    })

                }
            })

    return state.toString()

        }

    fun saveInDatabase(userEmail:String, userRole:String, jwtToken:String ):String{

        val db = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "usersDatabase"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()
        userDao.deleteLastUser()
        val obj = UserEntity(1,userEmail,userRole,jwtToken)
        userDao.insertUser(obj)
    //    val users: UserEntity = userDao.getUser()
        return "done"
    }






}


