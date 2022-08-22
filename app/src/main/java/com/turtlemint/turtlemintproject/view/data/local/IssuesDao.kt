package com.turtlemint.turtlemintproject.view.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.turtlemint.turtlemintproject.view.data.model.Issue

// for managing the database opertions
@Dao
interface IssuesDao {
    // get all issues by created date
    @Query("SELECT * FROM issue_table order by created_at DESC")
    fun getIssues() : LiveData<List<Issue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(issue: Issue) : Long

    // insert all issues
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(issue: List<Issue>)

    // delete issue
    @Delete
    suspend fun delete(issue: Issue)

    // delete all issues
    @Query("DELETE FROM issue_table")
    suspend fun deleteAllIssues()
}