package com.potter.triwizard.ui.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.potter.triwizard.R
import com.potter.triwizard.databinding.FragmentHousesBinding
import com.potter.triwizard.util.Status
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HousesFragment : Fragment() {
    private lateinit var binding: FragmentHousesBinding
    private lateinit var viewModel: HouseViewModel
    private lateinit var adapter: HousesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHousesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HouseViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HousesAdapter { house ->
                val action = HousesFragmentDirections.actionHousesFragmentToHouseDetailsFragment(houseId = house._id)
                findNavController().navigate(action)
            Toast.makeText(requireContext(), house.name, Toast.LENGTH_SHORT).show()
        }
        binding.homesRecyclerView.adapter = adapter
       binding.homesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        updateList()
    }

    private fun updateList() {
        viewModel.houses.observe(viewLifecycleOwner, Observer { houses ->
            when (houses.status) {
                Status.SUCCESS -> {
                    binding.progressBar.remove()
                    houses.message
                    houses.data?.let { adapter.submitList(it) }
                    binding.homesRecyclerView.show()
                }
                Status.LOADING -> {
                    binding.progressBar.show()
                    binding.homesRecyclerView.remove()
                }
                Status.ERROR -> {
                    binding.progressBar.remove()
                    Toast.makeText(requireContext(), getString(R.string.load_error_message), Toast.LENGTH_LONG).show()

                }
            }
        })
    }
}