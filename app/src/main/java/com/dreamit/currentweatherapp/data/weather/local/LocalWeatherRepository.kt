package com.dreamit.currentweatherapp.data.weather.local

import com.dreamit.currentweatherapp.data.weather.WeatherDataSource
import com.dreamit.currentweatherapp.weather.model.CityWeather
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalWeatherRepository(
        private val localStorage: Realm
) : WeatherDataSource {

    override fun getWeather(cityId: Long): Observable<CityWeather> =
            Observable.fromCallable {
                getWeatherFromDatabase(cityId)
            }

    private fun getWeatherFromDatabase(cityId: Long): CityWeather =
            runBlocking(UI) {
                val weather = localStorage.where(CityWeather::class.java)
                        .equalTo(CityWeather.WEATHER_CITY_ID, cityId)
                        .findFirst()

                weather?.let {
                    localStorage.copyFromRealm(it)
                } ?: CityWeather()
            }


    override fun saveWeather(cityWeather: CityWeather) {
        localStorage.executeTransactionAsync {
            it.insertOrUpdate(cityWeather)
        }
    }
}