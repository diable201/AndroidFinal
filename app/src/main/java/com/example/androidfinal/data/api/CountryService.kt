package com.example.androidfinal.data.api

import com.example.androidfinal.data.model.Country
import com.example.androidfinal.data.model.CountryDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.covid19api.com/"

fun createCountryService(): CountryService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(CountryService::class.java)
}

interface CountryService {
    @GET("countries")
    suspend fun getCountries(): Response<List<Country> >

    @GET("country/{slug}")
    suspend fun getCountryDetails(@Path("slug") slug: String): Response<List<CountryDetail> >
}