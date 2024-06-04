package com.cursosandroidant.forecastweather.mainModule.viewModel

import com.cursosandroidant.forecastweather.common.dataAccess.WeatherForecastService
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var service: WeatherForecastService

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
        service = retrofit.create(WeatherForecastService::class.java)
    }

    @Test
    fun checkCurrentWeatherIsNotNullTest() {

        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                19.4342, -99.1962,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en"
            )
            assertThat(result.current, `is`(notNullValue()) )
        }

    }

    @Test
    fun checkTimezoneReturnsMexicoCityTest() {

        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                19.4342, -99.1962,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en"
            )
            assertThat(result.timezone, `is`("America/Mexico_City") )
        }

    }

    @Test
    fun checkErrorResponseWithOnlyCoordinatesTest() {

        runBlocking {
            try {
                service.getWeatherForecastByCoordinates(
                    19.4342, -99.1962,
                    "", "", ""
                )
            } catch (e: Exception){
                assertThat(e.localizedMessage, `is`("HTTP 401 Unauthorized") )
            }



        }

    }
}