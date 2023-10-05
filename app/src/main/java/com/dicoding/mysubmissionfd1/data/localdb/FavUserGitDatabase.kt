package com.dicoding.mysubmissionfd1.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavUserGit::class], version = 1)
abstract class FavUserGitDatabase: RoomDatabase() {
    abstract fun FavUserDao(): FavUserDao

    companion object{
        @Volatile
        private var INSTANCE: FavUserGitDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavUserGitDatabase{
            if (INSTANCE == null){
                synchronized(FavUserGitDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavUserGitDatabase::class.java, "favusergit")
                        .build()
                }
            }
            return INSTANCE as FavUserGitDatabase
        }
    }
}