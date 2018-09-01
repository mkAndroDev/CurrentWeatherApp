package com.dreamit.currentweatherapp.weather.presenter

import com.dreamit.currentweatherapp.data.weather.WeatherRepository
import com.dreamit.currentweatherapp.weather.WeatherContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherPresenter(
        private val repository: WeatherRepository,
        private val view: WeatherContract.View
) : WeatherContract.Presenter {

    override fun getCurrentWeather(cityId: Long) {
        repository.getWeather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weather ->
                    if (weather.id > 0 && weather.name.isNotEmpty()) {
                        view.showCurrentWeather(weather)
                    }
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }
}