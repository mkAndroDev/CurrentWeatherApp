package com.dreamit.currentweatherapp.data.weathers

import com.dreamit.currentweatherapp.data.weathers.local.LocalHistoricalRepository
import com.dreamit.currentweatherapp.data.weathers.remote.RemoteWeathersRepository
import com.dreamit.currentweatherapp.weathers.model.Weather
import io.reactivex.Observable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class WeathersRepository(
        private val localRepository: LocalHistoricalRepository,
        private val remoteRepository: RemoteWeathersRepository
) : WeathersDataSource {

    override fun getHistoricalWeathers(): Observable<List<Weather>> {
        return Observable.concat(
                remoteRepository.getHistoricalWeathers(),
                localRepository.getHistoricalWeathers()
        )
    }

    override fun getWeather(city: String): Observable<Weather> {
        return Observable.concat(
                remoteRepository.getWeather(city).doOnNext {
                    if (it.id > 0 && it.name.isNotEmpty()) {
                        launch(UI) {
                            localRepository.saveWeather(it)
                        }
                    }
                },
                localRepository.getWeather(city))
    }

    override fun saveWeather(weather: Weather) {
        localRepository.saveWeather(weather)
    }
}