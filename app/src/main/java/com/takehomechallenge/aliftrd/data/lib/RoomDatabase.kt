package com.takehomechallenge.aliftrd.data.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import com.takehomechallenge.aliftrd.data.character.local.CharacterDao
import com.takehomechallenge.aliftrd.data.character.local.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = true,
)

abstract class RoomDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}