package com.turtlemint.turtlemintproject.view.ui.issuedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.repository.IssuesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueDetailsViewModel @Inject constructor(
    private val issuesRepository: IssuesRepository
) : ViewModel() {

    private val issueEventChannel = Channel<IssueEvent>()
    val issueEvent = issueEventChannel.receiveAsFlow()

    fun saveIssue(issue: Issue) {
        viewModelScope.launch {
            issuesRepository.insertIssue(issue)
            issueEventChannel.send(IssueEvent.ShowIssueSavedMessage("issue Saved."))
        }
    }
    // for adding all issues in database
    fun saveIssueList(issueList: List<Issue>) {
        viewModelScope.launch {
            issuesRepository.insertAllIssue(issueList)
            issueEventChannel.send(IssueEvent.ShowIssueSavedMessage("issue Saved."))
        }
    }

    sealed class IssueEvent{
        data class ShowIssueSavedMessage(val message: String): IssueEvent()
    }
}