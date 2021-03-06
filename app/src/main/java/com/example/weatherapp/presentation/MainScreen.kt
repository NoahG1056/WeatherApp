package com.example.weatherapp.screens

import android.content.Context
import android.os.AsyncTask.execute
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.models.CityName
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherInfoUseCase


@Composable
fun MainScreen(Context:Context,CityName:CityName) {

    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState,
                label = {
                    Text("City")
                },
                onValueChange = {
                    textFieldState = it
                }, singleLine = true,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    CityName(textFieldState)
                    val weatherRepository = WeatherRepositoryImpl(context = Context, CityName = CityName)
                    val temp = GetWeatherInfoUseCase(weatherRepository = weatherRepository).execute()
                    scaffoldState.snackbarHostState.showSnackbar("In City $temp")
                }

            }) {
                Text("Go")
            }
        }
    }
}