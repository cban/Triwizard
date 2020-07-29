package com.potter.triwizard.ui.spell

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potter.triwizard.data.Spell
import com.potter.triwizard.repository.SpellRepository
import com.potter.triwizard.util.Resource
import kotlinx.coroutines.launch

class SpellsViewModel @ViewModelInject constructor(private val repository: SpellRepository) :
    ViewModel() {
    private val _spells = MutableLiveData<Resource<List<Spell>>>()
    val spells: LiveData<Resource<List<Spell>>>
        get() = _spells

    init {
        getSpells()
    }

    private fun getSpells() {
        viewModelScope.launch {
            _spells.postValue(Resource.loading(null))
            repository.getHouses().let {
                if (it.isSuccessful) {
                    _spells.postValue(Resource.success(it.body()))
                } else _spells.postValue(
                    Resource.error(
                        it.errorBody().toString() + it.raw().body,
                        null
                    )
                )
            }
        }
    }
}
