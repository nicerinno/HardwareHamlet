package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Titles;

import java.util.List;

@Dao
public interface TitlesDAO {

    @Query("SELECT * FROM titles")
    List<Titles> getTitlesList();

    @Insert
    void insertTitles(List<Titles> titlesList);

    @Query("SELECT * FROM titles WHERE title_id = :id")
    Titles getTitleById(int id);

    @Query("DELETE FROM titles")
    void deleteTitlesList();

}
