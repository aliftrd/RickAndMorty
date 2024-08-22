package com.takehomechallenge.aliftrd.data.character.remote

import com.takehomechallenge.aliftrd.data.character.model.CharactersItem
import com.takehomechallenge.aliftrd.data.lib.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("/api/character")
    suspend fun getCharacters(): BaseResponse<List<CharactersItem>>

    @GET("/api/character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: String): CharactersItem

    @GET("/api/character")
    suspend fun searchCharacter(@Query("name") name: String): BaseResponse<List<CharactersItem>>
}