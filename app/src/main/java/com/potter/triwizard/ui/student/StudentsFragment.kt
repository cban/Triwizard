package com.potter.triwizard.ui.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.potter.triwizard.databinding.FragmentStudentsBinding

class StudentsFragment : Fragment() {

    private lateinit var binding: FragmentStudentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }
}