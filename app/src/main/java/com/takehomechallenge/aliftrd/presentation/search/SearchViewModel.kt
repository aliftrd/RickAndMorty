package com.takehomechallenge.aliftrd.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.model.Character
import kotlinx.coroutines.launch

class SearchViewModel(private val cuc: CharacterUseCase) : ViewModel() {
    private val _characters = MutableLiveData<ApiResponse<List<Character>>>()
    val characters: LiveData<ApiResponse<List<Character>>> get() = _characters

    fun searchCharacters(name: String) {
        viewModelScope.launch {
            if(name.isEmpty()) {
                _characters.value = ApiResponse.Success(emptyList())
                return@launch
            }

            cuc.searchCharacters(name).collect {
                _characters.value = it
            }
        }
    }
}