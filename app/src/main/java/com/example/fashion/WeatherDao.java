package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert
    void setInsertWeather(Weather weather);

    @Update
    void setUpdateWeather(Weather weather);

    @Delete
    void setDeleteWeather(Weather weather);

    @Query("Select * from Weather")
    List<Weather> getWeatherAll();

    @Query("SELECT * FROM Weather WHERE ID = :id")
    Weather getWeatherById(int id);


}
