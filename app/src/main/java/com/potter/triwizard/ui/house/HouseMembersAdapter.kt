package com.potter.triwizard.ui.house

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.potter.triwizard.data.Member
import com.potter.triwizard.databinding.MemberItemBinding

class HouseMembersAdapter : ListAdapter<Member, HouseMembersAdapter.ViewHolder>(DIFF_CALLBACKS) {
    private lateinit var binding: MemberItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HouseMembersAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = MemberItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(member: Member) {
            binding.memberNameText.text = member.name
        }
    }

    override fun onBindViewHolder(holder: HouseMembersAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        @VisibleForTesting
        val DIFF_CALLBACKS = object : DiffUtil.ItemCallback<Member>() {

            override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
                return true
            }
        }
    }
}