package com.potter.triwizard.ui.house

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.potter.triwizard.R
import com.potter.triwizard.data.House
import com.potter.triwizard.databinding.FragmentHousesBinding
import com.potter.triwizard.databinding.HouseItemBinding
import javax.inject.Inject

class HousesAdapter @Inject constructor(
    private val onItemClicked: (item: House) -> Unit
) : ListAdapter<House, HousesAdapter.ViewHolder>(DIFF_CALLBACKS) {
    private lateinit var binding: HouseItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = HouseItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(house: House) {
            binding.headOfHouse.text = house.headOfHouse
            binding.houseNameText.text = house.name
        }
    }

    override fun onBindViewHolder(holder: HousesAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(holder.adapterPosition))
        }
    }

    companion object {
        @VisibleForTesting
        val DIFF_CALLBACKS = object : DiffUtil.ItemCallback<House>() {

            override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
                return true
            }
        }
    }
}
