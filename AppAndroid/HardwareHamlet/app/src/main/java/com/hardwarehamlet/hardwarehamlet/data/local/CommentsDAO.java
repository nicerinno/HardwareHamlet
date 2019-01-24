package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Comments;

import java.util.List;
@Dao
public interface CommentsDAO {
    @Query("SELECT * FROM comments WHERE build_id = :build_id")
    List<Comments> getCommentsListByBuild(long build_id);

    @Insert
    void insertComments(List<Comments> commentsList);


    @Query("DELETE FROM comments WHERE build_id = :build_id")
    void deleteCommentsFromBuild(long build_id);
}
