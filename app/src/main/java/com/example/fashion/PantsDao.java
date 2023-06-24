package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PantsDao {
    @Insert
    void setInsertPants(Pants pants);

    @Update
    void setUpdatePants(Pants pants);

    @Delete
    void setDeletePants(Pants pants );

    //조회 쿼리
    @Query("select * from Pants ")
    List<Pants> getPantsAll();

    @Query("SELECT * FROM Pants WHERE ID = :id")
    Pants getPantsById(int id);
}
