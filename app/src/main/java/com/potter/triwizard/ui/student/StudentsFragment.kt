package com.potter.triwizard.ui.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.potter.triwizard.R
import com.potter.triwizard.data.Character
import com.potter.triwizard.databinding.FragmentStudentsBinding
import com.potter.triwizard.util.Status
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show

class StudentsFragment : Fragment() {
    private lateinit var adapter: StudentsAdapter
    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var binding: FragmentStudentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StudentsAdapter { student ->
//            val action =
//                HousesFragmentDirections.actionHousesFragmentToHouseDetailsFragment(houseId = house._id)
//            findNavController().navigate(action)
        }
        binding.studentRecyclerView.adapter = adapter
        updateList()
    }

    private fun updateList() {
        var filteredStudentsList: List<Character> = listOf()
        viewModel.students.observe(viewLifecycleOwner, Observer { characters ->
            when (characters.status) {
                Status.SUCCESS -> {
                    binding.studentsProgressBar.remove()
                    characters.message
                    characters.data?.let { students ->
                        filteredStudentsList = students.filter { it.role == CHARACTER_FILTER }
                    }
                    adapter.submitList(filteredStudentsList)
                    binding.studentRecyclerView.show()
                }

                Status.LOADING -> {
                    binding.studentsProgressBar.show()
                    binding.studentRecyclerView.remove()
                }
                Status.ERROR -> {
                    binding.studentsProgressBar.remove()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.load_error_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    companion object {
        const val CHARACTER_FILTER = "student"
    }
}