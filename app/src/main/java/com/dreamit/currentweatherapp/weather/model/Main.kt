package com.dreamit.currentweatherapp.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Main : RealmObject() {

    @Expose
    @SerializedName("temp")
    var temp: Double? = null
    @Expose
    @SerializedName("pressure")
    var pressure: String? = null
    @Expose
    @SerializedName("humidity")
    var humidity: String? = null
    @Expose
    @SerializedName("temp_min")
    var tempMin: Double? = null
    @Expose
    @SerializedName("temp_max")
    var tempMax: Double? = null
}