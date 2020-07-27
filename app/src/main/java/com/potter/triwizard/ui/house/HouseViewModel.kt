package com.potter.triwizard.ui.house

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potter.triwizard.Resource
import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.repository.HousesRepository
import kotlinx.coroutines.launch

class HouseViewModel @ViewModelInject constructor(
    private val repository: HousesRepository
) : ViewModel() {
    private val _houses = MutableLiveData<Resource<List<House>>>()
    val houses: LiveData<Resource<List<House>>>
        get() = _houses
    init {
        getHouses()
    }
    private fun getHouses() {
        viewModelScope.launch {
            _houses.postValue(Resource.loading(null))
            repository.getHouses().let {
                if (it.isSuccessful) {
                    _houses.postValue(Resource.success(it.body()))
                } else _houses.postValue(Resource.error(it.errorBody().toString()+it.raw().body, null))
            }
        }
    }
}