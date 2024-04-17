package com.example.weatherapi


import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query



fun createRetrofitClient(){
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(WeatherApiService::class.java)
    apiService.getWeatherForecast("86471d8bea5403ba3d152041241504", "Almaty", 3)
        .enqueue(object : retrofit2.Callback <WeatherForecast>{
            override fun onResponse(call: retrofit2.Call<WeatherForecast>, response: retrofit2.Response<WeatherForecast>) {
                val body = response.body()
                /*var loc = body?.location
                var current = body?.current
                var forecast = body?.forecast*/
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                throw Exception(t)
            }
        })
}

fun createClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor{
            Log.d("http", it)
        }.also { it.level = HttpLoggingInterceptor.Level.BASIC })
        .build()
}

interface WeatherApiService {
    @GET("forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") numDays: Int
    ): Call<WeatherForecast>
}

data class WeatherForecast(
    val location: Location,
    val current: CurrentWeather,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Long,
    val localtime: String
)

data class CurrentWeather(
    val last_updated_epoch: Long,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val pressure_mb: Double,
    val pressure_in: Double,
    val precip_mm: Double,
    val precip_in: Double,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val uv: Double,
    val gust_mph: Double,
    val gust_kph: Double
)


class Forecast {
    private val day = mutableListOf<ForecastDay>()

    val forecastday: List<ForecastDay>
        get() = day.toList()
}

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val maxwind_mph: Double,
    val maxwind_kph: Double,
    val totalprecip_mm: Double,
    val totalprecip_in: Double,
    val totalsnow_cm: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    val avghumidity: Int,
    val daily_will_it_rain: Int,
    val daily_chance_of_rain: Int,
    val daily_will_it_snow: Int,
    val daily_chance_of_snow: Int,
    val condition: Condition,
    val uv: Double
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

/*interface ApiServise {
    @POST("/current.json")
    suspend fun create(@Body entity: Entity): Response<Any>

    @GET
    suspend fun get("/current.json"): Response<Entity>
}

data class Entity(val temp_c: String, val is_day: String, val text: String,
                  val icon: String, val code: String, val cloud: String, val feelslike_c: String)*/