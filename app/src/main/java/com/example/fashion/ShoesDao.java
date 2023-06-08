package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoesDao {
    @Insert
    void setInsertShoes(Shoes shoes);

    @Update
    void setUpdateShoes(Shoes shoes);

    @Delete
    void setDeleteShoes(Shoes shoes);

    //조회 쿼리
    @Query("select * from Shoes")
    List<Shoes> getShoesAll();

    @Query("SELECT * FROM Shoes WHERE ID = :id")
    Shoes getShoesById(int id);
}
