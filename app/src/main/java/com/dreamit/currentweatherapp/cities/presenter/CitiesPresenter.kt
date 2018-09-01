package com.dreamit.currentweatherapp.cities.presenter

import com.dreamit.currentweatherapp.cities.CitiesContract
import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.data.cities.CitiesDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CitiesPresenter(
        private val repository: CitiesDataSource,
        private val view: CitiesContract.View
) : CitiesContract.Presenter {

    override fun getCities(cityName: String) {
        repository.getCities(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cityResponse ->
                    cityResponse.list?.let {
                        view.showCities(it)
                    }
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun getHistoricalCities() {
        repository.getHistoricalCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cities ->
                    if (cities.isNotEmpty()) {
                        view.showCities(cities)
                    }
                }, { error ->
                    view.showError(error.message.orEmpty())
                })
    }

    override fun saveCity(city: City) {
        repository.saveCity(city)
    }
}