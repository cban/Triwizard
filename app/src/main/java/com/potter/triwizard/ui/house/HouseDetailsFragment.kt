package com.potter.triwizard.ui.house

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.potter.triwizard.util.Resource
import com.potter.triwizard.data.House
import com.potter.triwizard.databinding.FragmentHouseDetailsBinding
import com.potter.triwizard.util.Status


class HouseDetailsFragment : Fragment() {
    private val viewModel: HouseViewModel by activityViewModels()
    private val args: HouseDetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentHouseDetailsBinding
    lateinit var house: LiveData<Resource<House>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.houseId?.let { viewModel.setId(it) }


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
        house.observe(viewLifecycleOwner, Observer { houseResource ->
            when (houseResource.status) {
                Status.SUCCESS -> {
                    houseResource.data.let {
                        binding.headOfHouseText.text = it?.headOfHouse
                        binding.founderText.text = it?.founder
                        binding.houseGhostText.text = it?.houseGhost
                        binding.houseNameText.text = it?.name
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
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