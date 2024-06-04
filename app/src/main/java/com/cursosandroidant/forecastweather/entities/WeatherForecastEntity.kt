package com.cursosandroidant.forecastweather.entities

/****
 * Project: Forecast Weather
 * From: com.cursosandroidant.forecastweather.entities
 * Created by Alain Nicolás Tello on 15/12/21 at 20:35
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
data class WeatherForecastEntity(val timezone: String,
                                 val current: Current,
                                 val hourly: List<Forecast>)