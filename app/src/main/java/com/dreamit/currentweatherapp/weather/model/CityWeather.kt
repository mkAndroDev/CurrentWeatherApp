package com.dreamit.currentweatherapp.weather.model

import com.dreamit.currentweatherapp.cities.model.Sys
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CityWeather : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("name")
    var name = ""
    @Expose
    @SerializedName("sys")
    var sys: Sys? = null
    @Expose
    @SerializedName("weather")
    var weathers: RealmList<Weather>? = null
    @Expose
    @SerializedName("main")
    var main: Main? = null

    companion object {
        const val WEATHER_CITY_NAME = "name"
    }
}