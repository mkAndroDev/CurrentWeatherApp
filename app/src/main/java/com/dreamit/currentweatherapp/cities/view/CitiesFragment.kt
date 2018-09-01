package com.dreamit.currentweatherapp.cities.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.currentweatherapp.MainActivity
import com.dreamit.currentweatherapp.R
import com.dreamit.currentweatherapp.cities.CitiesContract
import com.dreamit.currentweatherapp.cities.adapter.CitiesAdapter
import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.cities.presenter.CitiesPresenter
import com.dreamit.currentweatherapp.data.cities.CitiesRepository
import com.dreamit.currentweatherapp.data.cities.local.LocalCitiesRepository
import com.dreamit.currentweatherapp.data.cities.remote.RemoteCitiesRepository
import com.dreamit.currentweatherapp.weather.view.WeatherFragment
import kotlinx.android.synthetic.main.fragment_cities.*

class CitiesFragment : Fragment(), CitiesContract.View, CitiesAdapter.OnCitiesAdapterListener {

    private val localCitiesRepository by lazy {
        LocalCitiesRepository((activity as MainActivity).realm)
    }
    private val remoteCitiesRepository by lazy {
        RemoteCitiesRepository((activity as MainActivity).weathersService)
    }
    private val citiesRepository by lazy {
        CitiesRepository(localCitiesRepository, remoteCitiesRepository)
    }
    private val presenter by lazy {
        CitiesPresenter(citiesRepository, this)
    }
    private val citiesAdapter by lazy {
        CitiesAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.getHistoricalCities()
    }

    private fun initViews() {
        iv_search.setOnClickListener {
            if (et_search.text.isNotEmpty()) {
                presenter.getCities(et_search.text.toString())
            } else {
                presenter.getHistoricalCities()
            }
            (activity as MainActivity).hideKeyboard()
        }
        rv_cities.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = citiesAdapter
        }
    }

    override fun showCities(cities: List<City>) {
        if (cities.count() > 0) {
            citiesAdapter.resetCities(cities)
        }
    }

    override fun showError(error: String) {
        view?.let {
            Snackbar.make(it, error, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCityClicked(city: City) {
        city.name?.let {
            presenter.saveCity(city)
            et_search.setText("")
            (activity as MainActivity).loadFragment(WeatherFragment.newInstance(city.id), true)
        }
    }
}