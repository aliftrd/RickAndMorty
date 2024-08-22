package com.takehomechallenge.aliftrd.di

import androidx.room.Room
import com.takehomechallenge.aliftrd.data.lib.RoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            RoomDatabase::class.java,
            "rick_and_morty"
        ).build()
    }

    single { get<RoomDatabase>().characterDao() }
}