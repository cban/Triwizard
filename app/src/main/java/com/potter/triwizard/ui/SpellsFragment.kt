package com.potter.triwizard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potter.triwizard.databinding.ActivityMainBinding
import com.potter.triwizard.databinding.FragmentSpellsBinding


class SpellsFragment : Fragment() {

    private lateinit var binding: FragmentSpellsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpellsBinding.inflate(inflater, container, false)
        return binding.root
    }
}