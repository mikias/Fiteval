package com.fiteval.ui.item;

/**
 * Created by Henry Moon on 11/7/2016.
 */

public class LeaderboardListItem {
    private String rank;
    private String nickName;
    private String level;

    public LeaderboardListItem(String rank, String nickName, String level) {
        this.rank = rank;
        this.nickName = nickName;
        this.level = level;
    }

    public String rank() {
        return rank;
    }

    public String nickName() {
        return nickName;
    }

    public String level() {
        return level;
    }
}
