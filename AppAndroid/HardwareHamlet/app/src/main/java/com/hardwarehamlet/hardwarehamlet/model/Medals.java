package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Medals {
    @PrimaryKey
    private int medal_id;
    private String name;
    private String icon;
    private int amount_likes;

    public Medals(int medal_id, String name, String icon, int amount_likes) {
        this.medal_id = medal_id;
        this.name = name;
        this.icon = icon;
        this.amount_likes = amount_likes;
    }

    public int getMedal_id() {
        return medal_id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getAmount_likes() {
        return amount_likes;
    }
}
