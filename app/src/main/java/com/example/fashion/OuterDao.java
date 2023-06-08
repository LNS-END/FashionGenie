package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OuterDao {
    @Insert
    void setInsertOuter(com.example.fashion.Outer outer );

    @Update
    void setUpdateOuter(com.example.fashion.Outer outer );

    @Delete
    void setDeleteOuter(com.example.fashion.Outer outer );

    @Query("Select * from `Outer`")
    List<com.example.fashion.Outer> getOuterAll();

    @Query("SELECT * FROM `Outer` WHERE ID = :id")
    com.example.fashion.Outer getOuterById(int id);
}
