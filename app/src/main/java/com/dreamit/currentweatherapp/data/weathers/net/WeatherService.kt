package com.dreamit.currentweatherapp.data.weathers.net

import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("weather?q={city}&appid={appi}")
    fun getWeather(@Path("city") city: String, @Path("appid") appId: String): Observable<Weather>

}

////https://api.openweathermap.org/data/2.5/weather?q=Lowicz,pl&appid=215bab20e02e3067529fc4c0b1c67041