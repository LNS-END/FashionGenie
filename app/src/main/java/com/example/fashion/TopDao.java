package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fashion.Top;

import java.util.List;

@Dao
public interface TopDao {
    @Insert
    void setInsertTop(Top top);

    @Update
    void setUpdateTop(Top top);

    @Delete
    void setDeleteTop(Top top);

    //조회 쿼리
    @Query("select * from Top")
    List<Top> getTopAll();

    @Query("SELECT * FROM Top WHERE ID = :id")
    Top getTopById(int id);
}
