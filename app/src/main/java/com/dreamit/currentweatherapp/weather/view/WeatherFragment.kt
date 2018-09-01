package com.dreamit.currentweatherapp.weather.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.currentweatherapp.MainActivity
import com.dreamit.currentweatherapp.R
import com.dreamit.currentweatherapp.data.weather.WeatherRepository
import com.dreamit.currentweatherapp.data.weather.local.LocalWeatherRepository
import com.dreamit.currentweatherapp.data.weather.remote.RemoteWeatherRepository
import com.dreamit.currentweatherapp.weather.WeatherContract
import com.dreamit.currentweatherapp.weather.model.CityWeather
import com.dreamit.currentweatherapp.weather.presenter.WeatherPresenter

class WeatherFragment : Fragment(), WeatherContract.View {

    private val localRepository by lazy {
        LocalWeatherRepository((activity as MainActivity).realm)
    }
    private val remoteRepository by lazy {
        RemoteWeatherRepository((activity as MainActivity).weathersService)
    }
    private val repository by lazy {
        WeatherRepository(localRepository, remoteRepository)
    }
    private val presenter by lazy {
        WeatherPresenter(repository, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.getCurrentWeather("lodz,pl")
    }

    private fun initViews() {

    }

    override fun showCurrentWeather(cityWeather: CityWeather) {

    }

    override fun showError(error: String) {
        view?.let {
            Snackbar.make(it, error, Snackbar.LENGTH_SHORT).show()
        }
    }
}