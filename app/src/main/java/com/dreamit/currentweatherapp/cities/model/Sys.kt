package com.dreamit.currentweatherapp.cities.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Sys : RealmObject() {

    @Expose
    @SerializedName("country")
    var country = ""

}