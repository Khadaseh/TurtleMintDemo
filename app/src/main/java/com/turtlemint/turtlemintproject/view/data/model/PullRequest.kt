package com.turtlemint.turtlemintproject.view.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.turtlemint.turtlemintproject.view.util.DateUtil
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nullable
// pull request info
@Parcelize
data class PullRequest(
    val url: String,
    val html_url: String,
    val diff_url: String,
    val patch_url: String,
    val merged_at: String
) : Parcelable