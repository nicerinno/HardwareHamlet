package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.List;


@Dao
public interface Build_ComponentsDAO {

    @Query("SELECT * FROM components WHERE component_id in (SELECT component_id FROM build_components WHERE build_id = :build_id) AND component_type_id = :component_type")
    Components buildComponents(long build_id, int component_type);

    @Query("SELECT * FROM build_components WHERE component_id = :id AND build_id=:build_id")
    Build_Components getBuildComponentById(long id,long build_id);

    @Query("SELECT icon_url FROM components WHERE component_id = (SELECT component_id " +
            "FROM build_components WHERE build_id = :build_id AND component_id IN " +
            "(SELECT component_id FROM components WHERE component_type_id = 11))")
    String getCaseIcon(long build_id);


    @Query("SELECT * FROM build_components WHERE build_id = :build_id")
    List<Build_Components> getBuildComponentesByBuild(long build_id);

    @Insert
    void insertBuildComponent(Build_Components build_components);

    @Query("DELETE FROM build_components WHERE build_id = :build_id AND component_id in (select component_id FROM components WHERE component_type_id = :type_id)")
    void deleteBuildComponent(long build_id, int type_id);

    @Query("SELECT * FROM build_components WHERE build_id = :build_id AND component_id in (select component_id FROM components WHERE component_type_id = :type_id)")
    Build_Components getBuildComponentByType(long build_id, int type_id);

    @Query("SELECT * FROM build_components WHERE build_id = :build_id AND component_id in (select component_id FROM components WHERE component_type_id between 7 and 9)")
    Build_Components checkForStorage(long build_id);

    @Query("DELETE FROM build_components WHERE build_id = :build_id")
    void deleteBuildComponents(long build_id);

    @Query("SELECT round ((SELECT SUM(price) FROM components WHERE component_id IN (SELECT component_id FROM build_components WHERE build_id = :build_id)),2)")
    float getBuildPrice(long build_id);

    @Insert
    void insertBuildComponents(List<Build_Components> build_componentsList);
}
