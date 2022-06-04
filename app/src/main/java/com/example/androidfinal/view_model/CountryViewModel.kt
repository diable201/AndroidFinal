package com.example.androidfinal.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinal.data.model.Country
import com.example.androidfinal.data.model.CountryDetail
import com.example.androidfinal.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val countryList: MutableLiveData<List<Country> > = MutableLiveData()
    val countryDetailList: MutableLiveData<List<CountryDetail> > = MutableLiveData()

    fun loadCountryList() {
        viewModelScope.launch {
            val response = repository.getCountries()
            if (response.isSuccessful) {
                countryList.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun loadCountryDetails(slug: String) {
        viewModelScope.launch {
            val response = repository.getCountryDetails(slug = slug)
            if (response.isSuccessful) {
                var responseBody = response.body()!!
                responseBody = responseBody.sortedByDescending { it.date }
                countryDetailList.value = responseBody
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}