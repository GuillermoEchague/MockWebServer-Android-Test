package com.cursosandroidant.forecastweather.common.dataAccess

import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project: Forecast Weather
 * From: com.cursosandroidant.forecastweather.common.dataAccess
 * Created by Alain Nicolás Tello on 15/12/21 at 20:35
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
interface WeatherForecastService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String): WeatherForecastEntity
}