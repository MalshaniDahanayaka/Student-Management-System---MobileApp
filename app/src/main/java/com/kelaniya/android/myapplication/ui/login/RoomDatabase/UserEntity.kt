package com.kelaniya.android.myapplication.ui.login.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersDatabase")
data class UserEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "user_email") val userEmail: String?,
    @ColumnInfo(name = "user_role") val userRole: String?,
    @ColumnInfo(name = "jwt_token") val jwtToken: String?
)