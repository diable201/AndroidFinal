package com.example.androidfinal.repository

import com.example.androidfinal.data.api.CountryService
import com.example.androidfinal.data.model.Country
import com.example.androidfinal.data.model.CountryDetail
import retrofit2.Response

class CountryRepository(private val service: CountryService) {
    suspend fun getCountries(): Response<List<Country> > {
        return service.getCountries()
    }

    suspend fun getCountryDetails(slug: String): Response<List<CountryDetail> > {
        return service.getCountryDetails(slug = slug)
    }
}