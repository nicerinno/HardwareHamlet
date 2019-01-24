package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Builds;

import java.util.List;

@Dao
public interface BuildsDAO {
    @Query("SELECT MAX(build_id) FROM builds")
    long lastSavedBuildId();

    @Insert
    void inserBuildsList(List<Builds> buildsList);

    @Insert
    void insertBuild(Builds build);

    @Query("DELETE FROM builds WHERE build_id = :build_id")
    void deleteBuild(long build_id);

    @Query("SELECT * FROM builds ORDER BY regist_date ASC")
    List<Builds> getBuildsWithoutSearchAndTypeASC();
    @Query("SELECT * FROM builds ORDER BY regist_date DESC")
    List<Builds> getBuildsWithoutSearchAndTypeDESC();

    @Query("SELECT * FROM builds WHERE build_type_id = :type_id ORDER BY regist_date ASC")
    List<Builds> getBuildsWithoutSearchASC(int type_id);
    @Query("SELECT * FROM builds WHERE build_type_id = :type_id ORDER BY regist_date DESC")
    List<Builds> getBuildsWithoutSearchDESC(int type_id);

    @Query("SELECT * FROM builds WHERE build_name like :newSearch" +
            " or description like :newSearch ORDER BY regist_date ASC")
    List<Builds> getAllBuildsWithoutTypeASC(String newSearch);
    @Query("SELECT * FROM builds WHERE build_name like :newSearch ORDER BY regist_date DESC")
    List<Builds> getAllBuildsWithoutTypeDESC(String newSearch);

    @Query("SELECT * FROM builds WHERE build_type_id = :type_id AND build_name like :newSearch"+
            " or build_type_id = :type_id AND description like :newSearch ORDER BY price ASC")
    List<Builds> getAllBuildsWithTypeASC(String newSearch, int type_id);
    @Query("SELECT * FROM builds WHERE build_type_id = :type_id AND build_name like :newSearch"+
            " or build_type_id = :type_id AND description like :newSearch ORDER BY price DESC")
    List<Builds> getAllBuildsWithTypeDESC(String newSearch, int type_id);

    @Query("DELETE FROM builds")
    void deleAllBuilds();

    @Query("SELECT * FROM builds WHERE build_id = :build_id")
    Builds getBuildById(long build_id);

    @Query("UPDATE builds SET likes = :likes WHERE build_id=:build_id")
    void updateLikes(long likes, long build_id);
}
