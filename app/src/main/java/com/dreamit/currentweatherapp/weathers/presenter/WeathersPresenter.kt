package com.dreamit.currentweatherapp.weathers.presenter

import com.dreamit.currentweatherapp.weathers.WeathersContract

class WeathersPresenter(
        private val repository: String,
        private val view: WeathersContract.View
) : WeathersContract.Presenter {

    override fun getHistoricalWeathers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentWeather(cityName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}