package com.potter.triwizard.ui.spell

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.potter.triwizard.R
import com.potter.triwizard.databinding.FragmentSpellsBinding
import com.potter.triwizard.util.Status
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show


class SpellsFragment : Fragment() {

    private lateinit var binding: FragmentSpellsBinding
    private lateinit var adapter: SpellsAdapter
    private val viewModel: SpellsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpellsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SpellsAdapter()
        binding.spellsRecyclerView.adapter = adapter
        updateList()
    }

    private fun updateList() {
        viewModel.spells.observe(viewLifecycleOwner, Observer { spells ->
            when (spells.status) {
                Status.SUCCESS -> {
                    binding.spellsProgressBar.remove()
                    spells.message
                    spells.data?.let { adapter.submitList(it) }
                    binding.spellsRecyclerView.show()
                }
                Status.LOADING -> {
                    binding.spellsProgressBar.show()
                    binding.spellsRecyclerView.remove()
                }
                Status.ERROR -> {
                    binding.spellsProgressBar.remove()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.load_error_message),
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        })
    }
}