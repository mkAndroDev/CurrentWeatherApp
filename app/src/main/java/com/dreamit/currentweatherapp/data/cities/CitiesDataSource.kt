package com.dreamit.currentweatherapp.data.cities

import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.data.cities.model.CityResponse
import io.reactivex.Observable

interface CitiesDataSource {

    fun getCities(cityName: String): Observable<CityResponse>

    fun getHistoricalCities(): Observable<List<City>>

    fun saveCity(city: City)

}