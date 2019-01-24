package com.hardwarehamlet.hardwarehamlet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Components {
    @PrimaryKey
    private long component_id;
    private int component_type_id;
    private long user_id;
    private String brand;
    private String name;
    private String description;
    private double price;
    private int flg_available;
    private String icon_url;
    private long regist_date;

    public Components(long component_id, int component_type_id, long user_id, String brand, String name, String description, double price, int flg_available, String icon_url, long regist_date) {
        this.component_id = component_id;
        this.component_type_id = component_type_id;
        this.user_id = user_id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.flg_available = flg_available;
        this.icon_url = icon_url;
        this.regist_date = regist_date;
    }

    public long getComponent_id() {
        return component_id;
    }

    public int getComponent_type_id() {
        return component_type_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getFlg_available() {
        return flg_available;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public long getRegist_date() {
        return regist_date;
    }
}
