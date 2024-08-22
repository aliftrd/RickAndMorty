package com.takehomechallenge.aliftrd.di.feature

import com.takehomechallenge.aliftrd.data.character.CharacterDataStore
import com.takehomechallenge.aliftrd.data.character.CharacterRepository
import com.takehomechallenge.aliftrd.domain.character.CharacterInteractor
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import org.koin.dsl.module

val characterModule = module {
    factory<CharacterUseCase> { CharacterInteractor(get()) }
    factory<CharacterRepository> { CharacterDataStore(get(), get(), get()) }

    single { CharacterDataStore(get(), get(), get()) }
    single { CharacterInteractor(get()) }
}