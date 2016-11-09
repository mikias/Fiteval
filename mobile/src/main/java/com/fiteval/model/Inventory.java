package com.fiteval.model;

import java.util.ArrayList;

/**
 * Created by nader on 11/3/16.
 */

//TODO read from a save file

public class Inventory {

    ArrayList<Equipment> mEquipmentList;
    InventoryListener mListener;

    public Inventory(ArrayList<Equipment> list) {
        mEquipmentList = list;
    }

    public void setmListener(InventoryListener listener) {
        mListener = listener;
    }

    public Inventory addItem(Equipment item) {
        mEquipmentList.add(item);
        if(item.isEquipped() && mListener != null) {
            mListener.equipItem(item);
        }

        return this;
    }

    public interface InventoryListener {
        void equipItem(Equipment item);
    }
}
