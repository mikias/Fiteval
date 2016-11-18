package com.fiteval.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by nader on 11/3/16.
 */

//TODO create tostrings for all
public class Knight implements Inventory.InventoryListener {
    private int mGold;

    private Inventory mInv;

    private Equipment mHelmet;
    private Equipment mArmor;
    private Equipment mWeapon;
    private Equipment mBoots;

    private int mExperience;
    private int mExperienceRemaining;
    private int mLevel;

    private Genders mGender;
    private boolean mInit;

    private int mAge;
    public int steps;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private String u_id;

    public Knight() {
        new Knight(this.getu_id() ,this.getmGold(), this.getmLevel(), this.getmGender(), this.getmAge(), new Inventory(new ArrayList<Equipment>()));
    }

    public Knight(String user_id,int gold, int level, Genders gender, int age, Inventory inv){
        u_id = user_id;
        mGold = gold;
        mInv = inv;

        mInv.setmListener(this);

        mHelmet = new Equipment();
        mArmor = new Equipment();
        mWeapon = new Equipment();
        mBoots = new Equipment();

        mLevel = level;
        mExperience = 0;
        mExperienceRemaining = 1312;

        mGender = gender;
        mAge = age;

        mInit = true;
    }

    public String getu_id() {
        return u_id;
    }

    public void setu_id(String u_id) {
        this.u_id = u_id;
    }

    public boolean ismInit() {
        return mInit;
    }

    public Inventory getmInv() {
        return mInv;
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

    public int getmExperience() {
        return mExperience;
    }

    public void setmExperience(int mExperience) {
        this.mExperience = mExperience;
    }

    public int getmExperienceRemaining() {
        return mExperienceRemaining;
    }

    public void setmExperienceRemaining(int mExperienceRemaining) {
        this.mExperienceRemaining = mExperienceRemaining;
    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevelLevel) {
        this.mLevel = mLevelLevel;
    }

    public void incExp() {
        mExperience++;
    }

    public void setmAge(int age) {
        this.mAge = age;
    }

    public void levelUp() {
        if (mLevel != 50) {
            mLevel++;
            mExperience = 0;
            mExperienceRemaining = mLevel * 2 * 1312;
        } else {
            mExperience = 1;
            mExperienceRemaining = 0;
        }
    }
    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Genders getmGender() {
        return mGender;
    }

    public int getmAge() {
        return mAge;
    }

    //TODO add events that update exp and gold
    public interface KnightListener {
        void readKnight();
        void update(int exp, int gold);
    }

}
