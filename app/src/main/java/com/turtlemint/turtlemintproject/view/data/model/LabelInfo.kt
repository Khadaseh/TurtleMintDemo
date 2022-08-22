package com.turtlemint.turtlemintproject.view.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.turtlemint.turtlemintproject.view.util.DateUtil
import kotlinx.android.parcel.Parcelize
// label info
@Parcelize
data class LabelInfo(
    val id: Int,
    val node_id: String,
    val url: String,
    val name: String,
    val color: Int,
    val default: Boolean,
    val description: String
) : Parcelable