package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Comments {
    @PrimaryKey
    private long comment_id;
    private long build_id;
    private String content;
    private long user_id;
    private long regist_date;

    public Comments(long comment_id, long build_id, String content, long user_id, long regist_date) {
        this.comment_id = comment_id;
        this.build_id = build_id;
        this.content = content;
        this.user_id = user_id;
        this.regist_date = regist_date;
    }

    public long getComment_id() {
        return comment_id;
    }

    public long getBuild_id() {
        return build_id;
    }

    public String getContent() {
        return content;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getRegist_date() {
        return regist_date;
    }
}
