package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey
    private long user_id;
    private int title_id;
    private int usertype_id;
    private int medal_id;
    private String email;
    private String username;
    private String password;
    private String description;
    private int reputation;
    private boolean active;
    private String validation_code;
    private long regist_date;

    public Users(long user_id, int title_id, int usertype_id, int medal_id, String email, String username, String password, String description, int reputation, boolean active, String validation_code, long regist_date) {
        this.user_id = user_id;
        this.title_id = title_id;
        this.usertype_id = usertype_id;
        this.medal_id = medal_id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
        this.reputation = reputation;
        this.active = active;
        this.validation_code = validation_code;
        this.regist_date = regist_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public int getTitle_id() {
        return title_id;
    }

    public int getUsertype_id() {
        return usertype_id;
    }

    public int getMedal_id() {
        return medal_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public int getReputation() {
        return reputation;
    }

    public boolean isActive() {
        return active;
    }

    public String getValidation_code() {
        return validation_code;
    }

    public long getRegist_date() {
        return regist_date;
    }
}
