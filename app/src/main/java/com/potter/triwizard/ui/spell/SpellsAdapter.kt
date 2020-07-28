package com.potter.triwizard.ui.spell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.potter.triwizard.data.Spell
import com.potter.triwizard.databinding.SpellItemBinding
import javax.inject.Inject

class SpellsAdapter @Inject constructor() :
    ListAdapter<Spell, SpellsAdapter.ViewHolder>(DIFF_CALLBACKS) {
    private lateinit var binding: SpellItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpellsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = SpellItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spell: Spell) {
            binding.spellEffectText.text = spell.effect
            binding.spellNameText.text = spell.spell
            binding.spellTypeText.text = spell.type
        }
    }

    override fun onBindViewHolder(holder: SpellsAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        @VisibleForTesting
        val DIFF_CALLBACKS = object : DiffUtil.ItemCallback<Spell>() {

            override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return true
            }
        }
    }
}