package com.takehomechallenge.aliftrd.domain.character.mapper

import com.takehomechallenge.aliftrd.data.character.local.CharacterEntity
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter

fun List<CharacterEntity>.toDomain(): List<LocalCharacter> {
    return this.map {
        it.toDomain()
    }
}

fun CharacterEntity.toDomain(): LocalCharacter {
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

fun LocalCharacter.toEntity(): CharacterEntity {
    return CharacterEntity(
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