package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
@Entity
public class Builds {
    @PrimaryKey
    private long build_id;
    private long user_id;
    private int build_type_id;
    private String build_name;
    private String description;
    private double price;
    private long likes;
    private long regist_date;

    public Builds(long build_id, long user_id, int build_type_id, String build_name, String description, double price, long likes, long regist_date) {
        this.build_id = build_id;
        this.user_id = user_id;
        this.build_type_id = build_type_id;
        this.build_name = build_name;
        this.description = description;

        this.price = price;
        this.likes = likes;
        this.regist_date = regist_date;

    }

    public long getBuild_id() {
        return build_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public int getBuild_type_id() {
        return build_type_id;
    }

    public String getBuild_name() {
        return build_name;
    }

    public String getDescription() {
        return description;
    }


    public double getPrice() {
        return price;
    }

    public long getLikes() {
        return likes;
    }

    public long getRegist_date() {
        return regist_date;
    }

    public void setBuild_id(long build_id) {
        this.build_id = build_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setBuild_type_id(int build_type_id) {
        this.build_type_id = build_type_id;
    }

    public void setBuild_name(String build_name) {
        this.build_name = build_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public void setRegist_date(long regist_date) {
        this.regist_date = regist_date;
    }
}
