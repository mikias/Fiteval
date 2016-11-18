package com.fiteval.model;

/**
 * Created by mikias on 11/17/16.
 */

public class Knight_Info {

    private int gold, level,age;
    private  String u_id;

    public Knight_Info(String u_id, int age, int gold, int level) {
        this.u_id = u_id;
        this.age = age;
        this.gold = gold;
        this.level = level;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
