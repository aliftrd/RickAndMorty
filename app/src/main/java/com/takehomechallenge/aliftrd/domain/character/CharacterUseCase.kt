package com.takehomechallenge.aliftrd.domain.character

import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    fun getCharacters(): Flow<ApiResponse<List<Character>>>
    fun getCharacterDetail(id: String): Flow<ApiResponse<Character>>
    fun searchCharacters(name: String): Flow<ApiResponse<List<Character>>>

    // Local
    suspend fun saveLocalCharacters(character: LocalCharacter)
    suspend fun deleteLocalCharacters(characterId: String)
    fun getLocalCharacters(): Flow<List<LocalCharacter>>
    fun isFavorite(characterId: String): Flow<Boolean>
}