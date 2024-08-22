package com.takehomechallenge.aliftrd.data.character.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "origin")
    val origin: String,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "status")
    val status: String
)