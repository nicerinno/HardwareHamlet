package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Medals;

import java.util.List;

@Dao
public interface MedalsDAO {
    @Query("SELECT * FROM medals")
    List<Medals> getMedalsList();

    @Insert
    void insertMedals(List<Medals> titlesList);

    @Query("SELECT * FROM medals WHERE medal_id = :id")
    Medals getMedalById(int id);

    @Query("DELETE FROM medals")
    void deleteMedalsList();
}
