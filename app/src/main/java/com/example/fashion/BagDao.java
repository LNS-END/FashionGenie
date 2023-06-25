package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BagDao {
    @Insert
    void setInsertBag(com.example.fashion.Bag bag);

    @Update
    void setUpdateBag(com.example.fashion.Bag bag);

    @Delete
    void setDeleteBag(com.example.fashion.Bag bag);

    @Query("Select * from Bag")
    List<com.example.fashion.Bag> getBagAll();

    @Query("SELECT * FROM Bag WHERE ID = :id")
    com.example.fashion.Bag getBagById(int id);

}
