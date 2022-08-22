package com.turtlemint.turtlemintproject.view.ui.issue

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.repository.IssuesRepository
import com.turtlemint.turtlemintproject.view.util.NetworkUtil.Companion.hasInternetConnection
import com.turtlemint.turtlemintproject.view.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val issuesRepository: IssuesRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val issues: MutableLiveData<Resource<List<Issue>>> = MutableLiveData()
    var issuesResponse: List<Issue>? = null
    fun getAllIssues() = issuesRepository.getAllIssues()


    init {
        getIssues()
    }

    fun getIssues() = viewModelScope.launch {
        issuesApiCall()
    }


    private suspend fun issuesApiCall() {
        issues.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                val response = issuesRepository.getIssuesList()
                issues.postValue(handleBreakingNewsResponse(response))
            } else {
                issues.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (ex: Exception) {
            Log.e("Exception", "-->"+ex.cause +" -->"+ex.stackTrace +"-->"+ex.message)
            when (ex) {
                is IOException -> issues.postValue(Resource.Error("Network Failure"))
                else -> issues.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    //  for getting api response
    private suspend fun handleBreakingNewsResponse(response: Response<List<Issue>>): Resource<List<Issue>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (issuesResponse == null) {
                    issuesResponse = resultResponse
                    // adding list in database
                    issuesRepository.insertAllIssue(resultResponse)
                }
                return Resource.Success(issuesResponse ?: resultResponse) // return response
            }
        }
        return Resource.Error(response.message()) // return error if occurs
    }
}