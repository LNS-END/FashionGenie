package com.example.fashion;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.fashion.HeadWear.class,Bag.class, com.example.fashion.Outer.class, Pants.class, com.example.fashion.Shoes.class, com.example.fashion.Top.class,Coordi.class, com.example.fashion.Weather.class , com.example.fashion.LikedCoordi.class},version = 7)
public abstract class DataBase extends RoomDatabase {
    public abstract com.example.fashion.HeadWearDao headWearDao();
    public abstract BagDao bagDao();
    public abstract com.example.fashion.OuterDao outerDao();
    public abstract PantsDao pantsDao();
    public abstract com.example.fashion.ShoesDao shoesDao();
    public abstract com.example.fashion.TopDao topDao();

    public abstract  CoordiDao coordiDao();

    public abstract com.example.fashion.LikedCoordiDao likedCoordiDao();
    public abstract com.example.fashion.WeatherDao weatherDao();
}
