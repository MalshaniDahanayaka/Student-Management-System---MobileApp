package com.kelaniya.android.myapplication.ui.login.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM usersDatabase")
    fun getUser(): UserEntity

    @Insert
    fun insertUser(vararg users: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("DELETE FROM usersDatabase WHERE uid = 1")
    fun deleteLastUser()

}