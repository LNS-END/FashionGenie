package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CoordiDao {

    @Insert
    void setInsertCoordi(Coordi coordi);

    @Delete
    void setDeleteCoordi(Coordi coordi);

    @Update
    void setUpdateCoodi(Coordi coordi);

    @Query("select * from Coordi")
    List<Coordi> getCoordiAll();

    @Query("SELECT * FROM Coordi WHERE ID = :id")
    Coordi getCoordiById(int id);
}
