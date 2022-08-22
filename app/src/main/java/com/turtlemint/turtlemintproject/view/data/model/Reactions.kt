package com.turtlemint.turtlemintproject.view.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.turtlemint.turtlemintproject.view.util.DateUtil
import kotlinx.android.parcel.Parcelize
// reactions info
@Parcelize
data class Reactions(
    val url: String,
    val total_count: Int,
    val laugh: Int,
    val hooray: Int,
    val confused: Int,
    val heart: Int,
    val rocket: Int,
    val eyes: Int
) : Parcelable