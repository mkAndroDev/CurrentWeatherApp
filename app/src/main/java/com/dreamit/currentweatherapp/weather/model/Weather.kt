package com.dreamit.currentweatherapp.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Weather : RealmObject() {

    @Expose
    @SerializedName("main")
    var main: String? = null
    @Expose
    @SerializedName("description")
    var description: String? = null

}