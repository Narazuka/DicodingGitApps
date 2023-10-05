package com.dicoding.mysubmissionfd1.data.localdb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavUserGit(
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,
):Parcelable
