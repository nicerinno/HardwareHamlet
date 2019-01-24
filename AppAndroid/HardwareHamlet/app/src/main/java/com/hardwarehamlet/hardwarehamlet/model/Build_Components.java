package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(primaryKeys = {"build_id","component_id"})
public class Build_Components {
    private long build_id;
    private long component_id;
    private int quantity;

    public Build_Components(long build_id, long component_id, int quantity) {
        this.build_id = build_id;
        this.component_id = component_id;
        this.quantity = quantity;
    }

    public long getBuild_id() {
        return build_id;
    }

    public long getComponent_id() {
        return component_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBuild_id(long build_id) {
        this.build_id = build_id;
    }

    public void setComponent_id(long component_id) {
        this.component_id = component_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
