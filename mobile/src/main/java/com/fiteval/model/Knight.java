package com.fiteval.model;

import java.util.ArrayList;

import static com.fiteval.model.InvSlots.ARMOR;
import static com.fiteval.model.InvSlots.BOOTS;
import static com.fiteval.model.InvSlots.HELMET;
import static com.fiteval.model.InvSlots.WEAPON;

/**
 * Created by nader on 11/3/16.
 */

public class Knight implements Inventory.InventoryListener {
    private int mGold;
    private long mExp;

    private Inventory mInv;

    private Equipment mHelmet;
    private Equipment mArmor;
    private Equipment mWeapon;
    private Equipment mBoots;

    private boolean mInit;

    private String email;
    private String dob;
    private Genders gender;
    private String nickname;

    public Knight() {
        mInv = new Inventory(new ArrayList<Equipment>());
        mGold = 0;
        mExp = 0;

        mInv.setmListener(this);

        mHelmet = new Equipment();
        mArmor = new Equipment();
        mWeapon = new Equipment();
        mBoots = new Equipment();

        mInit = true;
    }

    public Knight(int gold, long exp, Inventory inv){
        mGold = gold;
        mExp = exp;
        mInv = inv;

        mInv.setmListener(this);

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
            case BOOTS:
                mBoots = item;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //TODO add events that update exp and gold
    public interface KnightListener {
        void readKnight();
        void update(int exp, int gold);
    }

}
