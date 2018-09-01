package com.dreamit.currentweatherapp.weathers.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Weather : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    val id: Long = 0
    @Expose
    @SerializedName("name")
    val name = ""

    companion object {
        const val WEATHER_CITY_NAME = "name"
    }
}