package com.dicoding.mysubmissionfd1.data.response

import com.google.gson.annotations.SerializedName


data class UsernameResponse(
    @field:SerializedName("items")
    val listUser: ArrayList<User>
)


