package com.example.weatherapp.data.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.domain.models.CityName
import com.example.weatherapp.domain.repository.WeatherRepository
import org.json.JSONObject

const val API_KEY="da440bc612f44429bae72612222105"

class WeatherRepositoryImpl(private val context: Context, private val CityName:CityName) : WeatherRepository {

    override fun getTemp(): String {
        val url = "http://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$CityName&aqi=no"
        var result = "Unknow"
        val queue= Volley.newRequestQueue(context)
        val stringRequest= StringRequest(
            Request.Method.GET,
            url,
            {
                    response ->
                val obj = JSONObject(response)
                result = obj.getJSONObject("current").getString("temp_c")

            },
            {
                    error->
                Log.d("MyLog","Error $error")
            }
        )
        queue.add(stringRequest)
        return result
    }
}