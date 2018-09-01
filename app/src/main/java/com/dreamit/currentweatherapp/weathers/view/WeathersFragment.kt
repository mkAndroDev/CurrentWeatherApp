package com.dreamit.currentweatherapp.weathers.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamit.currentweatherapp.R
import com.dreamit.currentweatherapp.weathers.WeathersContract
import com.dreamit.currentweatherapp.weathers.model.Weather
import com.dreamit.currentweatherapp.weathers.presenter.WeathersPresenter
import kotlinx.android.synthetic.main.fragment_weathers.*

class WeathersFragment : Fragment(), WeathersContract.View {

    private val presenter by lazy {
        WeathersPresenter("", this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weathers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        iv_search.setOnClickListener {
            if (et_search.text.isNotEmpty()) {
                presenter.getCurrentWeather(et_search.text.toString())
            } else {
                presenter.getHistoricalWeathers()
            }
        }

    }

    override fun showLastSearches(cities: List<Weather>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCurrentWeather(weather: Weather) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}