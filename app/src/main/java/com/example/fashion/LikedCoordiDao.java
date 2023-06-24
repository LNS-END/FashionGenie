package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LikedCoordiDao {
    @Insert
    void setInsertLikedCoordi(LikedCoordi likedCoordi);

    @Update
    void setUpdateLikedCoordi(LikedCoordi likedCoordi);

    @Delete
    void setDeleteLikedCoordi(LikedCoordi likedCoordi);

    @Query("Select * from LikedCoordi")
    List<LikedCoordi> getLikedCoordiAll();
}
