package com.turtlemint.turtlemintproject.view.data.local

import androidx.room.TypeConverter
import com.turtlemint.turtlemintproject.view.data.model.PullRequest
import com.turtlemint.turtlemintproject.view.data.model.Reactions
import com.turtlemint.turtlemintproject.view.data.model.User
import org.json.JSONObject
// to convert object on string & viceversa
class Converters {

    @TypeConverter
    fun fromUser(user: User): String {
        return JSONObject().apply {
            put("id", user.id)
            put("login", user.login)
            put("node_id", user.node_id)
            put("avatar_url", user.avatar_url)
            put("gravatar_id", user.gravatar_id)
            put("url", user.url)
            put("html_url", user.html_url)
            put("followers_url", user.followers_url)
            put("following_url", user.following_url)
            put("gists_url", user.gists_url)
            put("starred_url", user.starred_url)
            put("subscriptions_url", user.subscriptions_url)
            put("organizations_url", user.organizations_url)
            put("repos_url", user.repos_url)
            put("events_url", user.events_url)
            put("received_events_url", user.received_events_url)
            put("type", user.type)
            put("site_admin", user.site_admin)
        }.toString()
    }

    @TypeConverter
    fun toUser(user: String): User {
        val json = JSONObject(user)
        return User(
            json.get("id") as Int,
            json.getString("login"),
            json.getString("node_id"),
            json.getString("avatar_url"),
            json.getString("gravatar_id"),
            json.getString("url"),
            json.getString("html_url"),
            json.getString("followers_url"),
            json.getString("following_url"),
            json.getString("gists_url"),
            json.getString("starred_url"),
            json.getString("subscriptions_url"),
            json.getString("organizations_url"),
            json.getString("repos_url"),
            json.getString("events_url"),
            json.getString("received_events_url"),
            json.getString("type"),
            json.getBoolean("site_admin")
        )
    }


//    @TypeConverter
//    fun fromPullRequest(pullRequest: PullRequest?): String {
//        return JSONObject().apply {
//            put("url", pullRequest?.url)
//            put("html_url", pullRequest?.html_url)
//            put("diff_url", pullRequest?.diff_url)
//            put("patch_url", pullRequest?.patch_url)
//            put("merged_at", pullRequest?.merged_at)
//        }.toString()
//    }
//
//    @TypeConverter
//    fun toPullRequest(pullRequest: String?): PullRequest {
//        val json = JSONObject(pullRequest!!)
//        return PullRequest(
//            json.getString("url"),
//            json.getString("html_url"),
//            json.getString("diff_url"),
//            json.getString("patch_url"),
//            json.getString("merged_at")
//        )
//    }


    @TypeConverter
    fun fromReactions(reactions: Reactions?): String {
        return JSONObject().apply {
            put("url", reactions?.url)
            put("total_count", reactions?.total_count)
            put("laugh", reactions?.laugh)
            put("hooray", reactions?.hooray)
            put("confused", reactions?.confused)
            put("heart", reactions?.heart)
            put("rocket", reactions?.rocket)
            put("eyes", reactions?.eyes)
        }.toString()
    }

    @TypeConverter
    fun toReactions(reactions: String): Reactions {
        val json = JSONObject(reactions)
        return Reactions(
            json.getString("url"),
            json.getInt("total_count"),
            json.getInt("laugh"),
            json.getInt("hooray"),
            json.getInt("confused"),
            json.getInt("heart"),
            json.getInt("rocket"),
            json.getInt("eyes")
        )
    }

}