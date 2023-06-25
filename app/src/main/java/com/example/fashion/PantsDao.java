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
    void setInsertPants(com.example.fashion.Pants pants);

    @Update
    void setUpdatePants(com.example.fashion.Pants mPants);

    @Delete
    void setDeletePants(com.example.fashion.Pants pants );

    //조회 쿼리
    @Query("select * from Pants ")
    List<Pants> getPantsAll();

    @Query("SELECT * FROM Pants WHERE ID = :id")
    com.example.fashion.Pants getPantsById(int id);


}
