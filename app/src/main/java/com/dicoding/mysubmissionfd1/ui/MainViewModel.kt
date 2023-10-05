package com.dicoding.mysubmissionfd1.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.data.response.UsernameResponse
import com.dicoding.mysubmissionfd1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _username = MutableLiveData<ArrayList<User>>()
    val username: LiveData<ArrayList<User>> get() = _username


    fun getSearchUsername(query: String){
        ApiConfig.getApiService() //Memanggil Api yang telah diconfigurasi sebelumnya
            .getSearchUser(query)
            .enqueue(object : Callback<UsernameResponse>{
                override fun onResponse(
                    call: Call<UsernameResponse>,
                    response: Response<UsernameResponse>
                ) {
                    if (response.isSuccessful){
                        val responseUserList = response.body()
                        if (responseUserList != null){
                            val usernameList = responseUserList.listUser
                            _username.postValue(usernameList)
                        }
                    }
                }

                override fun onFailure(call: Call<UsernameResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
}