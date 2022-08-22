package com.turtlemint.turtlemintproject.view.repository


import com.turtlemint.turtlemintproject.view.data.local.IssuesDao
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.data.remote.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
// for accessing operation methods
@Singleton
class IssuesRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val issuesDao: IssuesDao
) {
    suspend fun getIssuesList(): Response<List<Issue>> {
        return apiInterface.getIssuesList()
    }

    fun getAllIssues() = issuesDao.getIssues()

    suspend fun insertIssue(issue: Issue) = issuesDao.insert(issue)

    suspend fun deleteIssue(issue: Issue) = issuesDao.delete(issue)

    suspend fun deleteAllIssues() = issuesDao.deleteAllIssues()

    suspend fun insertAllIssue(issueList: List<Issue>) = issuesDao.insertAll(issueList)
}