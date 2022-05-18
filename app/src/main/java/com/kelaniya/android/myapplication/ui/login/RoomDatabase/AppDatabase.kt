package com.kelaniya.android.myapplication.ui.login.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE:AppDatabase? = null

        fun getAppDatabase(context: Context):AppDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,AppDatabase::class.java,"usersDatabase"
                ).allowMainThreadQueries()
                    .build()
            }

            return INSTANCE
        }
    }
}