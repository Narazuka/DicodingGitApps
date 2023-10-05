package com.dicoding.mysubmissionfd1.data.retrofit


import com.dicoding.mysubmissionfd1.data.response.DetailUserResponse
import com.dicoding.mysubmissionfd1.data.response.User
import com.dicoding.mysubmissionfd1.data.response.UsernameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q")
        query: String
    ): Call<UsernameResponse>

    @GET("users/{username}")
    fun getUsernameDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getUsernameFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getUsernameFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}