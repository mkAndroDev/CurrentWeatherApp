package com.dreamit.currentweatherapp.data.weathers.local

import com.dreamit.currentweatherapp.data.weathers.WeathersDataSource
import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalHistoricalRepository(
        private val localStorage: Realm
) : WeathersDataSource {

    override fun getHistoricalWeathers(): Observable<List<Weather>> =
            Observable.fromCallable {
                getWeathersFromDatabase()
            }

    override fun getWeather(city: String): Observable<Weather> =
            Observable.fromCallable {
                getWeatherFromDatabase(city)
            }

    private fun getWeatherFromDatabase(city: String): Weather =
            runBlocking(UI) {
                val weather = localStorage.where(Weather::class.java)
                        .equalTo(Weather.WEATHER_CITY_NAME, city).findFirst()

                weather?.let {
                    localStorage.copyFromRealm(it)
                } ?: Weather()
            }

    private fun getWeathersFromDatabase(): List<Weather> =
            runBlocking(UI) {
                val weathers = localStorage.where(Weather::class.java).findAll()

                weathers?.let {
                    localStorage.copyFromRealm(it)
                } ?: listOf()
            }


    override fun saveWeather(weather: Weather) {
        localStorage.executeTransactionAsync {
            it.insertOrUpdate(weather)
        }
    }
}