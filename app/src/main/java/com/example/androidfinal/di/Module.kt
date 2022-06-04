package com.example.androidfinal.di

import com.example.androidfinal.data.api.createCountryService
import com.example.androidfinal.repository.CountryRepository
import com.example.androidfinal.view_model.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CountryViewModel(repository = get()) }
}

val repositoryModule = module {
    single { CountryRepository(service = get()) }
}

val networkModule = module {
    single { createCountryService() }
}