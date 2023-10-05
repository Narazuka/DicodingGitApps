package com.dicoding.mysubmissionfd1.ui.detail_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel:ViewModel() {
    private val _follower = MutableLiveData<ArrayList<User>>()
    val follower: LiveData<ArrayList<User>> get() = _follower
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun setUsernameFollowers(username: String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getUsernameFollowers(username)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        _isLoading.value = false
                        val responseBody = response.body()
                        if (responseBody != null){
                            _follower.postValue(responseBody!!)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })

    }
    fun getUsernameFollowers(): LiveData<ArrayList<User>>{
        return follower
    }

}

