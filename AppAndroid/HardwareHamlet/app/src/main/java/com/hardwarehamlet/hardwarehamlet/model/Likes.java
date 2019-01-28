package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(primaryKeys={"build_id","user_id"})
public class Likes {
    private long build_id;
    private long user_id;

    public Likes(long build_id, long user_id) {
        this.build_id = build_id;
        this.user_id = user_id;
    }

    public long getBuild_id() {
        return build_id;
    }

    public void setBuild_id(long build_id) {
        this.build_id = build_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
