package com.dicoding.mysubmissionfd1.ui.detail_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mysubmissionfd1.data.localdb.FavUserDao
import com.dicoding.mysubmissionfd1.data.localdb.FavUserGit
import com.dicoding.mysubmissionfd1.data.localdb.FavUserGitDatabase
import com.dicoding.mysubmissionfd1.data.response.DetailUserResponse
import com.dicoding.mysubmissionfd1.data.retrofit.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUsersViewModel(application: Application): AndroidViewModel(application) {
   private val _user = MutableLiveData <DetailUserResponse>()
    val user: LiveData<DetailUserResponse> get() = _user
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isFavUser = MutableLiveData<Boolean>()
    val isFavUser: LiveData<Boolean> get() = _isFavUser

    private var favUserDao: FavUserDao?
    private var favUserGitDatabase : FavUserGitDatabase

    init {
        favUserGitDatabase = FavUserGitDatabase.getDatabase(application)
        favUserDao = favUserGitDatabase?.FavUserDao()
    }

    fun setDetailUsers(username: String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getUsernameDetail(username)
            .enqueue(object: Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            _user.postValue(responseBody!!)
                        } else {
                            Log.d("Response Error", "$response")
                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            val username = responseBody?.username
                            val check = checkFavUser(username.toString())
                            val isFavorite = check == "1"
                            withContext(Dispatchers.Main){
                                _isFavUser.value = isFavorite
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    _isLoading.value = false
                }
            })
    }
    fun getUserDetails(): LiveData<DetailUserResponse>{
        return user
    }

   fun addToFav(username: String,avatarUrl: String){
       CoroutineScope(Dispatchers.IO).launch {
           val favUser = FavUserGit(
               username = username,
               avatarUrl = avatarUrl
           )
           favUserDao?.insertAddFav(favUser)
       }
    }
   suspend fun checkFavUser(username: String) = favUserDao?.checkUserFav(username)
   fun removeFav(username: String){
       CoroutineScope(Dispatchers.IO).launch{
           favUserDao?.deleteFromFav(username)
       }
   }
   fun getFavData(username: String): LiveData<FavUserGit>? = favUserDao?.getFavoriteUserByUsername(username)
}