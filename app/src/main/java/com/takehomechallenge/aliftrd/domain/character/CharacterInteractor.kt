package com.takehomechallenge.aliftrd.domain.character

import com.takehomechallenge.aliftrd.data.character.CharacterRepository
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class CharacterInteractor(
    private val cr: CharacterRepository
): CharacterUseCase {
    override fun getCharacters(): Flow<ApiResponse<List<Character>>> {
        return cr.getCharacters().flowOn(Dispatchers.IO)
    }

    override fun getCharacterDetail(id: String): Flow<ApiResponse<Character>> {
        return cr.getCharacterDetail(id).flowOn(Dispatchers.IO)
    }

    override fun searchCharacters(name: String): Flow<ApiResponse<List<Character>>> {
        return cr.searchCharacters(name).flowOn(Dispatchers.IO)
    }

    // Local
    override suspend fun saveLocalCharacters(character: LocalCharacter) = cr.saveLocalCharacters(character)
    override suspend fun deleteLocalCharacters(characterId: String) = cr.deleteLocalCharacters(characterId)

    override fun getLocalCharacters(): Flow<List<LocalCharacter>> {
        return cr.getLocalCharacters().flowOn(Dispatchers.IO)
    }

    override fun isFavorite(characterId: String): Flow<Boolean> {
        return cr.isFavorite(characterId).flowOn(Dispatchers.IO)
    }
}