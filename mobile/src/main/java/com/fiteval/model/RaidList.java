package com.fiteval.model;

import android.location.Location;

import com.fiteval.R;

import java.util.ArrayList;

/**
 * Created by nader on 11/15/16.
 */

public class RaidList {

    private static ArrayList<Raid> list = new ArrayList<>();

    public RaidList() {
        Location burrussLoc = new Location("");
        burrussLoc.setLatitude(37.228500);
        burrussLoc.setLongitude(-80.422860);

        Raid burruss = new Raid("Burruss Hall", burrussLoc, R.drawable.burruss);

        list.add(burruss);
    }
}
