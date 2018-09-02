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
import com.dreamit.currentweatherapp.utils.getCelsiusFormKelvins
import com.dreamit.currentweatherapp.utils.getReadableErrorMessage
import com.dreamit.currentweatherapp.weather.WeatherContract
import com.dreamit.currentweatherapp.weather.model.CityWeather
import com.dreamit.currentweatherapp.weather.presenter.WeatherPresenter
import kotlinx.android.synthetic.main.fragment_weather.*

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
    private var cityId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityId = arguments?.getLong("cityId", 0) ?: 0L
        presenter.getCurrentWeather(cityId)
    }

    override fun showCurrentWeather(cityWeather: CityWeather) {
        tv_weather_city.text = cityWeather.name
        tv_weather_country.text = cityWeather.sys?.country
        cityWeather.weathers?.let {
            it.first()?.let {
                tv_weather_title.text = it.main
                tv_weather_description.text = it.description
            }
        }
        cityWeather.main?.let {
            tv_weather_temperature.text = it.temp?.let { getCelsiusFormKelvins(it).toString() + getString(R.string.temperature_celsius_symbol) }
            tv_weather_pressure.text = it.pressure?.let { it + getString(R.string.pressure_symbol) }
            tv_weather_humidity.text = it.humidity?.let { it + getString(R.string.humidity_symbol) }
            tv_weather_temp_min.text = it.tempMin?.let { it + getString(R.string.temperature_celsius_symbol) }
            tv_weather_temp_max.text = it.tempMax?.let { it + getString(R.string.temperature_celsius_symbol) }
        }
    }

    override fun showError(error: String) {
        if (isAdded) {
            view?.let {
                Snackbar.make(it, getReadableErrorMessage(context!!, error), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(cityId: Long): WeatherFragment {
            val fragment = WeatherFragment()
            val args = Bundle()
            args.putLong("cityId", cityId)
            fragment.arguments = args
            return fragment
        }
    }
}