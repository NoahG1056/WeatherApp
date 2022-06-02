package com.example.weatherapp.domain.usecase

import android.content.Context
import android.util.Log
import androidx.compose.ui.text.createTextLayoutResult
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.domain.models.CityName
import com.example.weatherapp.domain.repository.WeatherRepository
import org.json.JSONObject


class GetWeatherInfoUseCase(private val weatherRepository: WeatherRepository){


}