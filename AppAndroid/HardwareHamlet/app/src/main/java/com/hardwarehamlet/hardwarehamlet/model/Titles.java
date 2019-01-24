package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Titles {
    @PrimaryKey
    private int title_id;
    private String name;
    private int rep_amount;
    private String color;

    public Titles(int title_id, String name, int rep_amount, String color) {
        this.title_id = title_id;
        this.name = name;
        this.rep_amount = rep_amount;
        this.color = color;
    }

    public int getTitle_id() {
        return title_id;
    }

    public String getName() {
        return name;
    }

    public int getRep_amount() {
        return rep_amount;
    }

    public String getColor() {
        return color;
    }
}
