package com.turtlemint.turtlemintproject.view.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.turtlemint.turtlemintproject.view.util.DateUtil
import kotlinx.android.parcel.Parcelize
// issue info
@Parcelize
@Entity(tableName = "issue_table")
class Issue(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int? = null,
    val title: String?,
    val body: String?,
    val updated_at: String?,
    val user: User?,
    val url: String?,
    val repository_url: String?,
    val labels_url: String?,
    val comments_url: String?,
    val events_url: String?,
    val html_url: String?,
    val node_id: String?,
    val number: Int?,
  //  val labels: ArrayList<String>?,
    val state: String?,
    val locked: Boolean?,
    val assignee: String?,
  //     val assignees: ArrayList<String>?,
 //   val milestone: String?,
    val comments: Int?,
    val created_at: String?,
  //  val closed_at: String?,
    val author_association: String?,
 //   val active_lock_reason: String?,
    val draft: Boolean?,
//    val pull_request: PullRequest?,
    val reactions: Reactions?,
    val timeline_url: String?
 //   val performed_via_github_app: String?,
 //   val state_reason: String?

) : Parcelable{
    val formattedPublishedAt : String get() {
        created_at?.let {
            return DateUtil.changeDateFormat(created_at)
        }
        return ""
    }
}
