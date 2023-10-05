package com.dicoding.mysubmissionfd1.data.response

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val userLogin: String,

    @field:SerializedName("avatar_url")
    val avatarurl: String,

    @field:SerializedName("id")
    val userId: Int? = null,

)
