package com.turtlemint.turtlemintproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turtlemint.turtlemintproject.view.data.model.Issue
import com.turtlemint.turtlemintproject.view.databinding.ItemIssueBinding
// Adapter for showing List
class IssuesAdapter(private val listener: OnItemClickListener): ListAdapter<Issue, IssuesAdapter.IssueViewHolder>(DiffCallback()) {

    // view creation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return IssueViewHolder(binding)
    }

    // Bind the views
    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class IssueViewHolder(private val binding: ItemIssueBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val issue = getItem(position)
                        listener.onItemClick(issue)
                    }
                }
            }
        }

        // set values to views
        fun bind(issue: Issue){
            binding.apply {
                // show avatar image
                Glide.with(itemView)
                    .load(issue.user?.avatar_url)
                    .into(ivAvatar)
               // Title
                tvTitle.text = issue.title

                // created date
                val sb = StringBuilder()
                sb.append("#").append(issue.number).append(" opened on ").append(issue.formattedPublishedAt).append(" by ").append(
                    issue.user?.login ?: ""
                )
                val c = sb.toString()
                tvPublishedAt.text = c
            }
        }
    }

    // item click of issue
    interface OnItemClickListener{
        fun onItemClick(issue: Issue)
    }


    class DiffCallback : DiffUtil.ItemCallback<Issue>(){
        override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem.formattedPublishedAt == newItem.formattedPublishedAt
        }

        override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem == newItem
        }

    }
}