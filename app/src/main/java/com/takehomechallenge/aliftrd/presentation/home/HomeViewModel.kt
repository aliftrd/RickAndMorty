package com.takehomechallenge.aliftrd.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.aliftrd.data.lib.ApiResponse

import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.model.Character
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cuc: CharacterUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<ApiResponse<List<Character>>>()
    val characters: LiveData<ApiResponse<List<Character>>> get() = _characters

    fun getCharacters() {
        viewModelScope.launch {
            cuc.getCharacters().collect {
                _characters.value = it
            }
        }
    }
}