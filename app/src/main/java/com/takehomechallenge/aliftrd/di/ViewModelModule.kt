package com.takehomechallenge.aliftrd.di

import com.takehomechallenge.aliftrd.presentation.detail.DetailViewModel
import com.takehomechallenge.aliftrd.presentation.favorite.FavoriteViewModel
import com.takehomechallenge.aliftrd.presentation.home.HomeViewModel
import com.takehomechallenge.aliftrd.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}