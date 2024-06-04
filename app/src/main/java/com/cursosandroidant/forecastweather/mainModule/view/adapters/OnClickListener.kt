package com.cursosandroidant.forecastweather.mainModule.view.adapters

import com.cursosandroidant.forecastweather.entities.Forecast

/****
 * Project: Forecast Weather
 * From: com.cursosandroidant.forecastweather.mainModule.view.adapters
 * Created by Alain Nicolás Tello on 15/12/21 at 20:42
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
interface OnClickListener {
    fun onClick(forecast: Forecast)
}