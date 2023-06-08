package com.example.fashion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HeadWearDao {

    @Insert
    void setInsertHeadWear(HeadWear headwear);

    @Update
    void setUpdateHeadWear(HeadWear headwear);

    @Delete
    void setDeleteHeadWear(HeadWear headwear);

    //조회 쿼리
    @Query("select * from HeadWear")
    List<HeadWear> getHeadWearAll();

    @Query("SELECT * FROM HeadWear WHERE ID = :id")
    HeadWear getHeadWearById(int id);


}

