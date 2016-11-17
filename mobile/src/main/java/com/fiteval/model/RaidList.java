package com.fiteval.model;

import android.location.Location;

import com.fiteval.R;

import java.util.ArrayList;

/**
 * Created by nader on 11/15/16.
 */

public class RaidList {

    public static ArrayList<Raid> createList() {
        Location burrussLoc = new Location("");
        ArrayList<Raid> list = new ArrayList<>();
        burrussLoc.setLatitude(37.228500);
        burrussLoc.setLongitude(-80.422860);

        Raid burruss = new Raid("Burruss Hall", burrussLoc, R.drawable.burruss);

        list.add(burruss);

        return list;
    }
}
