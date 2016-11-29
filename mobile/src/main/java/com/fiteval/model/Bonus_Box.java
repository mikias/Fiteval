package com.fiteval.model;

/**
 * Created by mikias on 11/14/16.
 */

public class Bonus_Box {

    String b_id,b_gps_longitude,b_gps_latitude,b_gold,b_info,b_expiration;


    public Bonus_Box(String b_expiration, String b_gold, String b_gps_latitude, String b_gps_longitude, String b_id, String b_info) {
        this.b_expiration = b_expiration;
        this.b_gold = b_gold;
        this.b_gps_latitude = b_gps_latitude;
        this.b_gps_longitude = b_gps_longitude;
        this.b_id = b_id;
        this.b_info = b_info;
    }

    public String getB_info() {
        return b_info;
    }

    public void setB_info(String b_info) {
        this.b_info = b_info;
    }

    public String getB_expiration() {
        return b_expiration;
    }

    public void setB_expiration(String b_expiration) {
        this.b_expiration = b_expiration;
    }

    public String getB_gold() {
        return b_gold;
    }

    public void setB_gold(String b_gold) {
        this.b_gold = b_gold;
    }

    public String getB_gps_latitude() {
        return b_gps_latitude;
    }

    public void setB_gps_latitude(String b_gps_latitude) {
        this.b_gps_latitude = b_gps_latitude;
    }

    public String getB_gps_longitude() {
        return b_gps_longitude;
    }

    public void setB_gps_longitude(String b_gps_longitude) {
        this.b_gps_longitude = b_gps_longitude;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }
}
