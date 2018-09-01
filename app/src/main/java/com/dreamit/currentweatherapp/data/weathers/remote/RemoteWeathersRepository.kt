package com.dreamit.currentweatherapp.data.weathers.remote

import android.util.Log
import com.dreamit.currentweatherapp.data.weathers.WeathersDataSource
import com.dreamit.currentweatherapp.net.APP_ID
import com.dreamit.currentweatherapp.net.WeatherService
import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable

class RemoteWeathersRepository(
        private val remoteRepository: WeatherService
) : WeathersDataSource {

    override fun getHistoricalWeathers(): Observable<List<Weather>> =
            Observable.fromCallable {
                listOf<Weather>()
            }

    override fun getWeather(city: String): Observable<Weather> =
            remoteRepository.getWeather(city, APP_ID)

    override fun saveWeather(weather: Weather) {
        Log.e(TAG, "Not implemented!")
    }

    companion object {
        private val TAG = RemoteWeathersRepository::class.java.simpleName
    }
}