package com.cursosandroidant.forecastweather.common.dataAccess

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection


@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun readJsonFileSuccess() {
        val reader = JSONFileLoader().loadJSONString("weather_forecast_response_success")
        assertThat(reader, `is`(notNullValue()))
        assertThat(reader, containsString("America/Mexico_City"))
    }

    @Test
    fun getWeatherForecastCheckTimezoneExist() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(
                JSONFileLoader().loadJSONString("weather_forecast_response_success")
                    ?: "{errorCode:1}"
            )
        mockWebServer.enqueue(response)

        assertThat(response.getBody()?.readUtf8(), containsString("timezone"))
    }

}