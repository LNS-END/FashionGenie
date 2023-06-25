package com.example.fashion;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;

import com.example.fashion.BagDao;
import com.example.fashion.Coordi;
import com.example.fashion.CoordiDao;
import com.example.fashion.HeadWear;
import com.example.fashion.HeadWearDao;
import com.example.fashion.LikedCoordi;
import com.example.fashion.LikedCoordiDao;
import com.example.fashion.Outer;
import com.example.fashion.OuterDao;
import com.example.fashion.Pants;
import com.example.fashion.PantsDao;
import com.example.fashion.ShoesDao;
import com.example.fashion.Top;
import com.example.fashion.TopDao;
import com.example.fashion.Weather;
import com.example.fashion.WeatherDao;

@Database(entities = {HeadWear.class, com.example.fashion.Bag.class, Outer.class, Pants.class, com.example.fashion.Shoes.class, Top.class, Coordi.class, Weather.class , LikedCoordi.class},version = 7)
public abstract class DataBase extends RoomDatabase {

    public abstract HeadWearDao headWearDao();
    public abstract BagDao bagDao();
    public abstract OuterDao outerDao();
    public abstract PantsDao pantsDao();
    public abstract ShoesDao shoesDao();
    public abstract TopDao topDao();

    public abstract CoordiDao coordiDao();

    public abstract LikedCoordiDao likedCoordiDao();
    public abstract WeatherDao weatherDao();

   private static DataBase instance;

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



}
