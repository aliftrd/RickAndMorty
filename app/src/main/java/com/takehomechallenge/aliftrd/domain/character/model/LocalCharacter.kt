package com.takehomechallenge.aliftrd.domain.character.model

data class LocalCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: String,
    val origin: String,
)