package com.dicoding.mysubmissionfd1.data.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse (
    @field:SerializedName("login")
    val userLogin: String,

    @field:SerializedName("id")
    val userId: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("followers")
    val userFollowers: Int,

    @field:SerializedName("following")
    val userFollowing: Int,

    @field:SerializedName("name")
    val username: String,

    val followers_url: String,
    val following_url: String,
)