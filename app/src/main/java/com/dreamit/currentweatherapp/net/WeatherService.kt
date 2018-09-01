package com.dreamit.currentweatherapp.net

import com.dreamit.currentweatherapp.data.cities.model.CityResponse
import com.dreamit.currentweatherapp.weather.model.CityWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("find")
    fun getCities(@Query("q") city: String, @Query("appid") appId: String): Observable<CityResponse>

    @GET("weather")
    fun getWeather(@Query("q") city: String, @Query("appid") appId: String): Observable<CityWeather>

}