package com.potter.triwizard.ui.house

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.repository.HouseRepository
import com.potter.triwizard.util.Resource
import kotlinx.coroutines.launch

class HouseViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: HouseRepository
) : ViewModel() {

    private val _houses = MutableLiveData<Resource<List<House>>>()
    val houses: LiveData<Resource<List<House>>>
        get() = _houses
    private val _selectedHouse = MutableLiveData<Resource<List<HouseResponse>>>()
    val selectedHouse: LiveData<Resource<List<HouseResponse>>>
        get() = _selectedHouse
    private var selectedHouseId: String = savedStateHandle.get<String>("id") ?: ""

    init {
        getHouses()
    }

    private fun getHouses() {
        viewModelScope.launch {
            try {
                _houses.postValue(Resource.loading(null))
                repository.getHouses().let {
                    if (it.isSuccessful) {
                        _houses.postValue(Resource.success(it.body()))
                    } else _houses.postValue(
                        Resource.error(
                            it.errorBody().toString() + it.raw().body,
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                _houses.postValue(
                    Resource.error(
                        e.message!!,
                        null
                    )
                )
            }
        }
    }

    fun setHouseId(id: String) {
        savedStateHandle["id"] = id
    }

    fun getHouse() {
        viewModelScope.launch {
            try {
                _selectedHouse.postValue(Resource.loading(null))
                repository.getHouseById(selectedHouseId).let {
                    if (it.isSuccessful) {
                        _selectedHouse.postValue(Resource.success(it.body()))
                    } else _houses.postValue(
                        Resource.error(
                            it.errorBody().toString() + it.raw().body,
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                _houses.postValue(
                    Resource.error(
                        e.message!!,
                        null
                    )
                )
            }
        }
    }
}