package com.takehomechallenge.aliftrd.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val cuc: CharacterUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<LocalCharacter>>()
    val characters: LiveData<List<LocalCharacter>> = _characters

    fun getLocalCharacters() {
        viewModelScope.launch {
            cuc.getLocalCharacters().collect {
                _characters.value = it
            }
        }
    }
}