package com.potter.triwizard.ui.house

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.potter.triwizard.R
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.databinding.FragmentHouseDetailsBinding
import com.potter.triwizard.ui.HomeActivity
import com.potter.triwizard.util.*

class HouseDetailsFragment : Fragment() {
    private val viewModel: HouseViewModel by activityViewModels()
    private val args: HouseDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentHouseDetailsBinding
    private lateinit var house: LiveData<Resource<List<HouseResponse>>>
    private lateinit var adapter: HouseMembersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.houseId?.let { viewModel.setHouseId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHouseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHouse()
        house = viewModel.selectedHouse
        setupView()
    }

    private fun setupView() {
        adapter = HouseMembersAdapter()
        binding.studentsRecyclerView.adapter = adapter
        house.observe(viewLifecycleOwner, Observer { houseResource ->
            when (houseResource.status) {
                Status.SUCCESS -> {
                    houseResource.data.let { it ->
                        binding.headOfHouseText.text = it?.get(0)?.headOfHouse
                        binding.founderText.text = it?.get(0)?.founder
                        binding.houseGhostText.text = it?.get(0)?.houseGhost
                        binding.houseNameText.text = it?.get(0)?.name
                        binding.ourValuesText.text = it?.get(0)?.values?.concat()
                        adapter.submitList(it?.get(0)?.members)
                        binding.houseDetailsProgressBar.remove()
                        binding.studentsRecyclerView.show()
                    }
                }
                Status.LOADING -> {
                    binding.houseDetailsProgressBar.show()
                    binding.studentsRecyclerView.show()
                }
                Status.ERROR -> {
                    binding.houseDetailsProgressBar.remove()
                    binding.studentsRecyclerView.remove()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.load_error_message_house_details),
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