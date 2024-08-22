package com.takehomechallenge.aliftrd.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.mapper.toLocal
import com.takehomechallenge.aliftrd.domain.character.model.Character
import kotlinx.coroutines.launch

class DetailViewModel(private val cuc: CharacterUseCase): ViewModel() {
    private val _character = MutableLiveData<ApiResponse<Character>>()
    val character: LiveData<ApiResponse<Character>> get() = _character

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getDetailCharacter(characterId: String) {
        viewModelScope.launch {
            cuc.getCharacterDetail(characterId).collect {
                _character.value = it
            }
        }
    }

    // Local
    fun saveToFavorite(character: Character) {
        viewModelScope.launch {
            cuc.saveLocalCharacters(character.toLocal())
            _isFavorite.value = true
        }
    }

    fun deleteFromFavorite(characterId: String) {
        viewModelScope.launch {
            cuc.deleteLocalCharacters(characterId)
            _isFavorite.value = false
        }
    }

    fun checkIsFavorite(characterId: String) {
        viewModelScope.launch {
            cuc.isFavorite(characterId).collect {
                _isFavorite.value = it
            }
        }
    }
}