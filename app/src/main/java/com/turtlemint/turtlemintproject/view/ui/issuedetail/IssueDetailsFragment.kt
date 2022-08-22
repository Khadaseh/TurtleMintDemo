package com.turtlemint.turtlemintproject.view.ui.issuedetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.turtlemint.turtlemintproject.view.R
import com.turtlemint.turtlemintproject.view.databinding.FragmentIssueinfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueDetailsFragment : Fragment(R.layout.fragment_issueinfo) {

    private val viewModel: IssueDetailsViewModel by viewModels()
    // getting data from previous fragment
    private val args by navArgs<IssueDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentIssueinfoBinding.bind(view)
        binding.apply {
            val issue = args.issue
            Log.e("issue","-->"+issue.title)
            Glide.with(view)
                .load(issue.user?.avatar_url)
                .into(ivAvatar)
            tvTitle.text = issue.title
            val sb = StringBuilder()
            sb.append(issue.user?.login ?: "").append(" opened this issue on ").append(issue.formattedPublishedAt)
            val c = sb.toString()
            tvPublishedAt.text = c
            tvBody.text = issue.body
        }
    }
}