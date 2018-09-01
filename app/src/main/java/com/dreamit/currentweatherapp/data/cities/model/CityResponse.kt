package com.dreamit.currentweatherapp.data.cities.model

import com.dreamit.currentweatherapp.cities.model.City
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse {

    @Expose
    @SerializedName("message")
    var message: String? = null
    @Expose
    @SerializedName("code")
    var code: String? = null
    @Expose
    @SerializedName("count")
    var count: Int? = null
    @Expose
    @SerializedName("list")
    var list: List<City>? = null

}