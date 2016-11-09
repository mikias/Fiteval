package com.fiteval.model;

import java.util.ArrayList;

import static com.fiteval.model.InvSlots.ARMOR;
import static com.fiteval.model.InvSlots.HELMET;
import static com.fiteval.model.InvSlots.WEAPON;

/**
 * Created by nader on 11/3/16.
 */

//TODO create tostrings for all
public class Knight implements Inventory.InventoryListener {
    private int mGold;
    private long mExp;

    private Inventory mInv;

    private Equipment mHelmet;
    private Equipment mArmor;
    private Equipment mWeapon;
    private Equipment mBoots;

    private boolean mInit;

    public Knight() {
        new Knight(2000, 10000, new Inventory(new ArrayList<Equipment>()));
    }

    public Knight(int gold, long exp, Inventory inv){
        mGold = gold;
        mExp = exp;
        mInv = inv;

        mInv.setmListener(this);

        mHelmet = new Equipment();
        mArmor = new Equipment();
        mWeapon = new Equipment();
        mBoots = new Equipment();

        mInit = true;
    }

    public boolean ismInit() {
        return mInit;
    }

    public Inventory getmInv() {
        return mInv;
    }

    public long getmExp() {
        return mExp;
    }

    public void setmExp(long mExp) {
        this.mExp = mExp;
    }

    public int getmGold() {
        return mGold;
    }

    public void setmGold(int mGold) {
        this.mGold = mGold;
    }

    public void equipItem(Equipment item) {
        switch(item.slot()) {
            case HELMET:
                mHelmet = item;
                break;
            case ARMOR:
                mArmor = item;
                break;
            case WEAPON:
                mWeapon = item;
                break;
        }
    }

    public Equipment getmHelmet() {
        return mHelmet;
    }

    public void setmHelmet(Equipment mHelmet) {
        this.mHelmet = mHelmet;
    }

    public Equipment getmArmor() {
        return mArmor;
    }

    public void setmArmor(Equipment mArmor) {
        this.mArmor = mArmor;
    }

    public Equipment getmWeapon() {
        return mWeapon;
    }

    public void setmWeapon(Equipment mWeapon) {
        this.mWeapon = mWeapon;
    }

    public Equipment getmBoots() {
        return mBoots;
    }

    public void setmBoots(Equipment mBoots) {
        this.mBoots = mBoots;
    }

    //TODO add events that update exp and gold
    public interface KnightListener {
        void readKnight();
        void update(int exp, int gold);
    }

}
