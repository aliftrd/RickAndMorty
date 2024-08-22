package com.takehomechallenge.aliftrd.data.character

import android.content.Context
import com.takehomechallenge.aliftrd.data.character.local.CharacterDao
import com.takehomechallenge.aliftrd.data.character.remote.CharacterService
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.mapper.toDomain
import com.takehomechallenge.aliftrd.domain.character.mapper.toEntity
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter
import com.takehomechallenge.aliftrd.utils.toApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDataStore(
    private val cs: CharacterService,
    private val cDao: CharacterDao,
    private val c: Context
) : CharacterRepository {
    override fun getCharacters(): Flow<ApiResponse<List<Character>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = cs.getCharacters()

            if (response.results != null) {
                val characters = response.results.toDomain()
                emit(ApiResponse.Success(characters))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(e.toApiResponse(c))
        }
    }

    override fun getCharacterDetail(id: String): Flow<ApiResponse<Character>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = cs.getCharacterDetail(id)

            val character = response.toDomain()
            emit(ApiResponse.Success(character))
        } catch (e: Exception) {
            emit(e.toApiResponse(c))
        }
    }

    override fun searchCharacters(name: String): Flow<ApiResponse<List<Character>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = cs.searchCharacter(name)

            if (response.results != null) {
                val characters = response.results.toDomain()
                emit(ApiResponse.Success(characters))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(e.toApiResponse(c))
        }
    }

    // Local
    override suspend fun saveLocalCharacters(character: LocalCharacter) = cDao.insertCharacter(character.toEntity())
    override suspend fun deleteLocalCharacters(characterId: String) = cDao.deleteCharacterById(characterId.toInt())

    override fun getLocalCharacters(): Flow<List<LocalCharacter>> = flow {
        val characterEntities = cDao.getAllCharacters()
        emit(characterEntities.toDomain())
    }

    override fun isFavorite(characterId: String): Flow<Boolean> = flow {
        val isFavorite = cDao.getCharacterById(characterId.toInt())
        emit(isFavorite != null)
    }
}