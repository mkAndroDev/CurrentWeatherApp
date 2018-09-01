package com.dreamit.currentweatherapp.data.weather

import com.dreamit.currentweatherapp.data.weather.local.LocalWeatherRepository
import com.dreamit.currentweatherapp.data.weather.remote.RemoteWeatherRepository
import com.dreamit.currentweatherapp.weather.model.CityWeather
import io.reactivex.Observable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class WeatherRepository(
        private val localRepository: LocalWeatherRepository,
        private val remoteRepository: RemoteWeatherRepository
) : WeatherDataSource {

    override fun getWeather(cityId: Long): Observable<CityWeather> {
        return Observable.concat(
                localRepository.getWeather(cityId),
                remoteRepository.getWeather(cityId).doOnNext {
                    if (it.id > 0) {
                        launch(UI) {
                            saveWeather(it)
                        }
                    }
                }
        )
    }

    override fun saveWeather(cityWeather: CityWeather) {
        localRepository.saveWeather(cityWeather)
    }
}