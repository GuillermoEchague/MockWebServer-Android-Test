package com.cursosandroidant.forecastweather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.forecastweather.common.dataAccess.WeatherForecastService
import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.cursosandroidant.forecastweather.mainModule.model.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****
 * Project: Forecast Weather
 * From: com.cursosandroidant.forecastweather.mainModule.viewModel
 * Created by Alain Nicolás Tello on 15/12/21 at 20:41
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class MainViewModel : ViewModel() {
    private val result = MutableLiveData<WeatherForecastEntity>()

    fun getResult(): LiveData<WeatherForecastEntity> = result

    private val repository: MainRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherForecastService::class.java)
        MainRepository(service)
    }

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String, lang: String){
        viewModelScope.launch {
            val resultServer = repository.getWeatherAndForecast(lat, lon, appId, units, lang)
            result.value = resultServer
        }
    }
}