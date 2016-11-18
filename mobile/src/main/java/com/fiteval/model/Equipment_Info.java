package com.fiteval.model;

/**
 * Created by mikias on 11/17/16.
 */

public class Equipment_Info {

    private boolean equipped,purchased;
    private String name;
    private int cost;
    private String u_id;

    public Equipment_Info( String u_id, String name, int cost, boolean equipped, boolean purchased) {

        this.u_id = u_id;
        this.cost = cost;
        this.equipped = equipped;
        this.name = name;
        this.purchased = purchased;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}
