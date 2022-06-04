package com.example.androidfinal.data.model

import com.google.gson.annotations.SerializedName

class CountryDetail (
    @SerializedName("ID")
    var id: String,
    @SerializedName("Country")
    var country: String,
    @SerializedName("CountryCode")
    var countryCode: String,
    @SerializedName("Province")
    var province: String,
    @SerializedName("CityCode")
    var cityCode: String,
    @SerializedName("Lat")
    var lat: Float,
    @SerializedName("Lon")
    var lon: Float,
    @SerializedName("Confirmed")
    var confirmed: Int,
    @SerializedName("Deaths")
    var deaths: Int,
    @SerializedName("Recovered")
    var recovered: Int,
    @SerializedName("Active")
    var active: Int,
    @SerializedName("Date")
    var date: String
) {
    override fun equals(other: Any?): Boolean {
        return other is CountryDetail &&
                other.id == id
    }


    override fun hashCode(): Int {
        return id.hashCode()
    }
}