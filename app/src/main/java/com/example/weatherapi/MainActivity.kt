package com.example.weatherapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapi.ui.theme.WeatherApiTheme
import androidx.compose.ui.unit.sp


private lateinit var weather: WeatherForecast

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val apiService = createRetrofitClient()

        super.onCreate(savedInstanceState)
        setContent {
            WeatherApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen(weather)
                }
            }
        }
    }


    @Composable
    fun WeatherScreen(weather: WeatherForecast) {


        Column {
            Text(
                text = "${weather.location.name}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        WeatherApiTheme {
            WeatherScreen(weather)
        }
    }
}

/*
@Composable
fun WeatherApp(weather: WeatherForecast) {
    // Определяем состояние для хранения информации о теме
    val isDay = remember { mutableStateOf(WeatherForecast.current.is_day == 1) }

    // Определяем тему в зависимости от состояния isDay
    val theme = if (isDay.value) {
        AppTheme.Day
    } else {
        AppTheme.Night
    }

    // Устанавливаем тему для всего приложения
    MaterialTheme(colors = theme.colors) {
        // Весь ваш пользовательский интерфейс будет применять выбранную тему
        WeatherScreen(weatherData = weatherData, isDay = isDay)
    }
}*/