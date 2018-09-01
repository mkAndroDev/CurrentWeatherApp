package com.dreamit.currentweatherapp.data.cities.remote

import android.util.Log
import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.data.cities.CitiesDataSource
import com.dreamit.currentweatherapp.data.cities.model.CityResponse
import com.dreamit.currentweatherapp.net.APP_ID
import com.dreamit.currentweatherapp.net.WeatherService
import io.reactivex.Observable

class RemoteCitiesRepository(
        private val remoteRepository: WeatherService
) : CitiesDataSource {

    override fun getCities(cityName: String): Observable<CityResponse> =
            remoteRepository.getCities(cityName, APP_ID)

    override fun getHistoricalCities(): Observable<List<City>> =
            Observable.fromCallable {
                listOf<City>()
            }

    override fun saveCity(city: City) {
        Log.e(TAG, "saveCity -> Not implemented!")
    }

    companion object {
        private val TAG = RemoteCitiesRepository::class.java.simpleName
    }
}