package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Component_Type {

    @NonNull
    @PrimaryKey
    private int component_type_id;
    private String name;

    public Component_Type(int component_type_id, String name) {
        this.component_type_id = component_type_id;
        this.name = name;
    }

    public int getComponent_type_id() {
        return component_type_id;
    }

    public String getName() {
        return name;
    }
}
