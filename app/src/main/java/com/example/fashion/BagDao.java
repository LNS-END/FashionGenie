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
    void setInsertBag(Bag bag);

    @Update
    void setUpdateBag(Bag bag);

    @Delete
    void setDeleteBag(Bag bag);

    @Query("Select * from Bag")
    List<Bag> getBagAll();

    @Query("SELECT * FROM Bag WHERE ID = :id")
    Bag getBagById(int id);

}
