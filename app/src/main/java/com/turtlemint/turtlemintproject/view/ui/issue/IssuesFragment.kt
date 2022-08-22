package com.turtlemint.turtlemintproject.view.ui.issue

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.turtlemint.turtlemintproject.view.R
import com.turtlemint.turtlemintproject.view.adapter.IssuesAdapter
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.databinding.FragmentIssuesBinding
import com.turtlemint.turtlemintproject.view.ui.issuedetail.IssueDetailsViewModel
import com.turtlemint.turtlemintproject.view.util.NetworkUtil
import com.turtlemint.turtlemintproject.view.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_issues.*

private const val TAG = "IssuesFragment"
@AndroidEntryPoint
class IssuesFragment: Fragment(R.layout.fragment_issues), IssuesAdapter.OnItemClickListener{

    private val viewModel: IssuesViewModel by viewModels()
    private val issueDetailsViewModel: IssueDetailsViewModel by viewModels()
    var isLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentIssuesBinding.bind(view)
        val issueAdapter = IssuesAdapter(this)

        binding.apply {
            rvBreakingNews.apply {
                adapter = issueAdapter
                setHasFixedSize(true)
            }
        }

        if (NetworkUtil.hasInternetConnection(requireActivity())) {
            // for Online List show
            viewModel.issues.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        // for show/hide progress
                        progressBar.visibility = View.INVISIBLE
                        isLoading = false
                        it.data?.let { issueResponse ->
                            issueAdapter.submitList(issueResponse)
                            issueDetailsViewModel.saveIssueList(issueResponse)
                        }
                    }
                    is Resource.Error -> {
                        // for error handling
                        progressBar.visibility = View.INVISIBLE
                        isLoading = true
                        it.message?.let { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "Error: $message")
                        }
                    }
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }else{
            // For Offline list show
            progressBar.visibility = View.INVISIBLE
            viewModel.getAllIssues().observe(viewLifecycleOwner) {
                issueAdapter.submitList(it)
            }
        }
    }

    // for navigating from one fragment to another fragment with data
    override fun onItemClick(issue: Issue) {
           val action = IssuesFragmentDirections.actionIssuesFragmentToIssueDetailsFragment(issue)
           findNavController().navigate(action)
    }
}