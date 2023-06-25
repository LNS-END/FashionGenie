package com.example.fashion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {
    @POST("v1Chat")
    Call<ResponseBody> postChatMessage(@Body Question question);

    @POST("v1weather")
    Call<ResponseBody> postWeatherData(@Body WeatherData weatherData);
}
