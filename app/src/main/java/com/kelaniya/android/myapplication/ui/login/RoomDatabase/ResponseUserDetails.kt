package com.kelaniya.android.myapplication.ui.login.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ResponseUserDetails(val uid: Int,
                               val userEmail: String?,
                               val userRole: String?,
                               val jwtToken: String?
                               )
