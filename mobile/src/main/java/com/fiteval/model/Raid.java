package com.fiteval.model;

import android.location.Location;

/**
 * Created by nader on 11/15/16.
 *
 */

public class Raid {
    private String name;
    private Location location;
    private int photo;

    public Raid(String name, Location location, int photo) {
        this.name = name;
        this.location = location;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getPhoto() {
        return photo;
    }
}