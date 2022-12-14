package com.potter.triwizard.ui.student

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potter.triwizard.data.Character
import com.potter.triwizard.repository.CharacterRepository
import com.potter.triwizard.util.Resource
import kotlinx.coroutines.launch

class StudentViewModel @ViewModelInject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
    private var selectedStudentId: String = ""
    private var _students = MutableLiveData<Resource<List<Character>>>()
    val students: MutableLiveData<Resource<List<Character>>>
        get() = _students
    private val _selectedStudent = MutableLiveData<Resource<Character>>()
    val selectedStudent: LiveData<Resource<Character>>
        get() = _selectedStudent

    init {
        getStudents()
    }

    private fun getStudents() {
        viewModelScope.launch {
            _students.postValue(Resource.loading(null))
            characterRepository.getStudents().let {
                if (it.isSuccessful) {
                    _students.postValue(Resource.success(it.body()))
                } else _students.postValue(
                    Resource.error(
                        it.errorBody().toString() + it.raw().body,
                        null
                    )
                )
            }
        }
    }

    fun setId(id: String) {
        selectedStudentId = id
    }

    fun getStudent() {
        viewModelScope.launch {
            try {
                _selectedStudent.postValue(Resource.loading(null))
                characterRepository.getCharacterById(selectedStudentId).let {
                    if (it.isSuccessful) {
                        _selectedStudent.postValue(Resource.success(it.body()))
                    } else _selectedStudent.postValue(
                        Resource.error(
                            it.errorBody().toString() + it.raw().body,
                            null
                        )
                    )
                }
            } catch (e: Throwable) {
                _selectedStudent.postValue(
                    Resource.error(
                        e.message!!,
                        null
                    )
                )
            }
        }
    }
}