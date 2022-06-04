package com.example.androidfinal.data.model

import com.google.gson.annotations.SerializedName

class Country (
    @SerializedName("Country")
    var country: String,
    @SerializedName("Slug")
    var slug: String,
    @SerializedName("ISO2")
    var iso2: String,
) {
    override fun equals(other: Any?): Boolean {
        return other is Country &&
                other.country == country

    }

    override fun hashCode(): Int {
        return country.hashCode()
    }
}