package com.fiteval.model;

/**
 * Created by nader on 11/3/16.
 */

public class Equipment {
    //TODO: create all the items from the doc
    //TODO: create a save exporter
    private boolean equipped;
    private boolean purchased;
    private String name;
    private int cost;
    private InvSlots slot;

    public Equipment () {
        this.equipped = true;
        this.purchased = true;
        this.name = "empty";
        this.cost = 0;
    }

    public Equipment (boolean equipped, boolean purchased, String name, int cost, InvSlots slot) {

        this.equipped = equipped;
        this.purchased = purchased;
        this.name = name;
        this.cost = cost;
        this.slot = slot;
    }

    public InvSlots slot() {
        return slot;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public boolean isEquipped() {
        return equipped;
    }
}
