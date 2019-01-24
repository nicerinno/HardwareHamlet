package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Build_Type;

import java.util.List;

@Dao
public interface Build_TypeDAO {

    @Query("SELECT name FROM build_type WHERE build_type_id= :id")
    String getBuildTypeName(int id);

    @Query("SELECT * FROM build_type")
    List<Build_Type> getBuildTypeList();

    @Query("SELECT name FROM build_type")
    List<String> getBuildTypeStringList();

    @Insert
    void insertBuildType(List<Build_Type> build_typeList);

    @Query("DELETE FROM build_type")
    void deleteBuildTypeList();
}
