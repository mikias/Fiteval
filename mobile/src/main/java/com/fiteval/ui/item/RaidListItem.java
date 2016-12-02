package com.fiteval.ui.item;

/**
 * Created by Henry Moon on 12/2/2016.
 */

public class RaidListItem {
    public String title;
    public String description;
    public String startDate;
    public String endDate;
    public int picture;
    public double latitude;
    public double longitude;
    public int gold;
    public boolean isClaimed;

    public RaidListItem(String title, String description, String startDate, String endDate,
                        int picture, double latitude, double longitude, int gold) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.picture = picture;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gold = gold;
    }
}
