package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Build_Type {
    @PrimaryKey
    private int build_type_id;
    private String name;

    public Build_Type(int build_type_id, String name) {
        this.build_type_id = build_type_id;
        this.name = name;
    }

    public int getBuild_type_id() {
        return build_type_id;
    }

    public String getName() {
        return name;
    }

    public void setBuild_type_id(int build_type_id) {
        this.build_type_id = build_type_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
