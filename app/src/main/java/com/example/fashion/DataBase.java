package com.example.fashion;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;

@Database(entities = {com.example.fashion.HeadWear.class,Bag.class, com.example.fashion.Outer.class, com.example.fashion.Pants.class, com.example.fashion.Shoes.class, com.example.fashion.Top.class,Coordi.class,Weather.class ,LikedCoordi.class},version = 7)
public abstract class DataBase extends RoomDatabase {

    public abstract HeadWearDao headWearDao();
    public abstract BagDao bagDao();
    public abstract OuterDao outerDao();
    public abstract PantsDao pantsDao();
    public abstract ShoesDao shoesDao();
    public abstract TopDao topDao();

    public abstract  CoordiDao coordiDao();

    public abstract  LikedCoordiDao likedCoordiDao();
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
