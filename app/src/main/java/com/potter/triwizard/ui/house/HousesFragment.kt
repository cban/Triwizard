package com.potter.triwizard.ui.house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.potter.triwizard.R
import com.potter.triwizard.databinding.FragmentHousesBinding
import com.potter.triwizard.util.Status
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show

class HousesFragment : Fragment() {
    private val viewModel: HouseViewModel by activityViewModels()
    private lateinit var binding: FragmentHousesBinding
    private lateinit var adapter: HousesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HousesAdapter { house ->
            val action =
                HousesFragmentDirections.actionHousesFragmentToHouseDetailsFragment(houseId = house._id)
            findNavController().navigate(action)
        }
        binding.homesRecyclerView.adapter = adapter
        binding.homesRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        updateHouseList()
    }

    private fun updateHouseList() {
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