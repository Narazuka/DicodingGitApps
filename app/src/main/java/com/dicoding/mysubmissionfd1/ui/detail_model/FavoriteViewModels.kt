package com.dicoding.mysubmissionfd1.ui.detail_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.mysubmissionfd1.data.localdb.FavUserDao
import com.dicoding.mysubmissionfd1.data.localdb.FavUserGit
import com.dicoding.mysubmissionfd1.data.localdb.FavUserGitDatabase
import com.dicoding.mysubmissionfd1.data.response.User

class FavoriteViewModels(application: Application): AndroidViewModel(application) {
    private var favUserDao: FavUserDao?
    private var favUserGitDatabase : FavUserGitDatabase

    init {
        favUserGitDatabase = FavUserGitDatabase.getDatabase(application)
        favUserDao = favUserGitDatabase?.FavUserDao()
    }
    fun getFavUser(): LiveData<List<FavUserGit>>?{
        return favUserDao?.getALLFavUserGit()
    }

    private fun FavUserGit.toItemsItem(): User{
        return User (
            userLogin = this.username,
            avatarurl = this.avatarUrl,
        )
    }

}