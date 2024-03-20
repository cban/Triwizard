package com.potter.triwizard.ui.student

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.potter.triwizard.R
import com.potter.triwizard.databinding.FragmentStudentDetailsBinding
import com.potter.triwizard.ui.HomeActivity
import com.potter.triwizard.util.Status
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show


class StudentDetailsFragment : Fragment() {
    lateinit var binding: FragmentStudentDetailsBinding
    private val viewModel: StudentViewModel by activityViewModels()
    private val args: StudentDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.characterId.let { viewModel.setId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStudent()
        setupView()
    }

    private fun setupView() {
        viewModel.selectedStudent.observe(viewLifecycleOwner, Observer { houseResource ->
            when (houseResource.status) {
                Status.SUCCESS -> {
                    houseResource.data.let { studentDetails ->
                        binding.studentNames.text = studentDetails?.name
                        binding.studentHouseText.text = studentDetails?.house
                        binding.ministryOfMagicValue.text =
                            studentDetails?.ministryOfMagic.toString()
                        binding.orderOfThePhoenixValue.text =
                            studentDetails?.orderOfThePhoenix.toString()
                        binding.dumbleDoresArmyValue.text =
                            studentDetails?.dumbledoresArmy.toString()
                        binding.deathEaterValue.text = studentDetails?.deathEater.toString()
                        binding.bloodStatusValue.text = studentDetails?.bloodStatus
                        binding.speciesValue.text = studentDetails?.species
                        binding.studentDetailsProgressBar.remove()
                        binding.constraintLayoutDetails.show()
                    }
                }
                Status.LOADING -> {
                    binding.studentDetailsProgressBar.show()
                    binding.constraintLayoutDetails.remove()

                }
                Status.ERROR -> {
                    binding.studentDetailsProgressBar.remove()
                    binding.constraintLayoutDetails.remove()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.load_error_message_student_details),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HomeActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as HomeActivity).showBottomNavigation()
        super.onDetach()
    }

}