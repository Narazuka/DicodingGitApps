package com.dicoding.mysubmissionfd1.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddFav(favUser: FavUserGit)

    @Query("SELECT count(*) FROM FavUserGit WHERE username = :username")
    suspend fun checkUserFav(username: String): String

    @Query("SELECT * FROM FavUserGit WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavUserGit>

    @Query("DELETE FROM FavUserGit WHERE username = :username")
    suspend fun deleteFromFav(username: String)


    @Query("SELECT * FROM FavUserGit ORDER BY username ASC")
    fun getALLFavUserGit(): LiveData<List<FavUserGit>>
}