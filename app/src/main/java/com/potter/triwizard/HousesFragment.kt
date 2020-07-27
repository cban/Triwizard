package com.potter.triwizard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potter.triwizard.databinding.FragmentHousesBinding

class HousesFragment : Fragment() {
    private lateinit var binding: FragmentHousesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }
}