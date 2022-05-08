package com.kelaniya.android.myapplication.utils

import android.content.Context
import androidx.room.Room
import com.kelaniya.android.myapplication.ui.login.RoomDatabase.AppDatabase
import com.kelaniya.android.myapplication.ui.login.RoomDatabase.ResponseUserDetails

class GetUserDetails {

    //get user details
    fun getData(context: Context): ResponseUserDetails {

        val appContext: Context = context.getApplicationContext()

        val db = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "usersDatabase"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        return ResponseUserDetails(
            userDao.getUser().uid,
            userDao.getUser().userEmail,
            userDao.getUser().userRole,
            userDao.getUser().jwtToken
        )
        //    val users: UserEntity = userDao.getUser()
    }

}