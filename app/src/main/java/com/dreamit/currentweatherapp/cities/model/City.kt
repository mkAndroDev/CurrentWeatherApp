package com.dreamit.currentweatherapp.cities.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class City : RealmObject() {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Long = 0
    @Expose
    @SerializedName("name")
    var name: String? = null
    @Expose
    @SerializedName("sys")
    var sys: Sys? = null

    companion object {
        const val CITY_NAME_FIELD = "name"
    }
}