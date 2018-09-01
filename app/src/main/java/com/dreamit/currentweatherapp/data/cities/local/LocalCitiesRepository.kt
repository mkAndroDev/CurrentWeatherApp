package com.dreamit.currentweatherapp.data.cities.local

import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.data.cities.CitiesDataSource
import com.dreamit.currentweatherapp.data.cities.model.CityResponse
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.runBlocking

class LocalCitiesRepository(
        private val localStorage: Realm
) : CitiesDataSource {

    override fun getCities(cityName: String): Observable<CityResponse> =
            Observable.fromCallable {
                CityResponse().apply {
                    list = getCitiesFromDatabase(cityName)
                }
            }

    override fun getHistoricalCities(): Observable<List<City>> =
            Observable.fromCallable {
                getCitiesFromDatabase()
            }

    private fun getCitiesFromDatabase(): List<City> =
            runBlocking(UI) {
                val cities = localStorage.where(City::class.java).findAll()

                cities?.let {
                    localStorage.copyFromRealm(it)
                } ?: listOf()
            }

    private fun getCitiesFromDatabase(city: String): List<City> =
            runBlocking(UI) {
                val cities = localStorage.where(City::class.java)
                        .equalTo(City.CITY_NAME_FIELD, city)
                        .findAll()

                cities?.let {
                    localStorage.copyFromRealm(it)
                } ?: listOf()
            }

    override fun saveCity(city: City) {
        localStorage.executeTransactionAsync {
            it.insertOrUpdate(city)
        }
    }
}