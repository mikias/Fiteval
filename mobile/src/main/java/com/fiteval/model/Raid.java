package com.fiteval.model;

import android.location.Location;

import com.fiteval.ui.activity.MainActivity;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nader on 11/15/16.
 *
 */

public class Raid {
    private String name;
    private Location location;
    private int photo;

    //TODO make this a time instead so it can check to see if it's been used recently
    private boolean used;

    public Raid(String name, Location location, int photo) {
        this.name = name;
        this.location = location;
        this.photo = photo;
        this.used = false;
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
    
    public void useRaid() {
        MainActivity.knight.setmGold(
                ThreadLocalRandom.current().nextInt(25, 76) + MainActivity.knight.getmGold());
        used = true;
    }
}
