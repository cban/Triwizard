package com.potter.triwizard.ui.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.potter.triwizard.data.Character
import com.potter.triwizard.databinding.StudentItemBinding
import javax.inject.Inject

class StudentsAdapter @Inject constructor(
    private val onItemClicked: (item: Character) -> Unit
) : ListAdapter<Character, StudentsAdapter.ViewHolder>(
    DIFF_CALLBACKS
) {
    private lateinit var binding: StudentItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = StudentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(student: Character) {
            binding.characterHouse.text = student.house
            binding.characterRoleText.text = student.role
            binding.studentNameText.text = student.name
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(holder.adapterPosition))
        }
    }

    companion object {
        @VisibleForTesting
        val DIFF_CALLBACKS = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return true
            }
        }
    }
}