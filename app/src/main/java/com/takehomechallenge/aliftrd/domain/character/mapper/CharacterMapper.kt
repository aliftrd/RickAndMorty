package com.takehomechallenge.aliftrd.domain.character.mapper

import com.takehomechallenge.aliftrd.data.character.model.CharactersItem
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter

fun List<CharactersItem>.toDomain(): List<Character> {
    return this.map {
        it.toDomain()
    }
}

fun CharactersItem.toDomain(): Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.name,
        location = this.location.name,
        image = this.image,
        episode = this.episode,
        url = this.url
    )
}

fun Character.toLocal(): LocalCharacter {
    return LocalCharacter(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        image = this.image,
        location = this.location,
        origin = this.origin
    )
}