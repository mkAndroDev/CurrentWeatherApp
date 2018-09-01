package com.dreamit.currentweatherapp.data.cities

import com.dreamit.currentweatherapp.cities.model.City
import com.dreamit.currentweatherapp.data.cities.local.LocalCitiesRepository
import com.dreamit.currentweatherapp.data.cities.model.CityResponse
import com.dreamit.currentweatherapp.data.cities.remote.RemoteCitiesRepository
import io.reactivex.Observable

class CitiesRepository(
        private val localRepository: LocalCitiesRepository,
        private val remoteRepository: RemoteCitiesRepository
) : CitiesDataSource {

    override fun getCities(cityName: String): Observable<CityResponse> {
        return Observable.concat(
                remoteRepository.getCities(cityName),
                localRepository.getCities(cityName)
        )
    }

    override fun getHistoricalCities(): Observable<List<City>> {
        return Observable.concat(
                remoteRepository.getHistoricalCities(),
                localRepository.getHistoricalCities()
        )
    }

    override fun saveCity(city: City) {
        localRepository.saveCity(city)
    }
}