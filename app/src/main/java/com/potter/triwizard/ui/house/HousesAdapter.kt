package com.potter.triwizard.ui.house

import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.potter.triwizard.data.House
import javax.inject.Inject

class HousesAdapter @Inject constructor(
    private var houses: List<House>, private val onItemClicked: (item: House) -> Unit
) : ListAdapter<House, HousesAdapter.ViewHolder>(DIFF_CALLBACKS) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HousesAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(house: House) {
        }
    }

    override fun onBindViewHolder(holder: HousesAdapter.ViewHolder, position: Int) {
        holder.bind(houses[position])
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
