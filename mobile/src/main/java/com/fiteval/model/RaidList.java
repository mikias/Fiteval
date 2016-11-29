package com.fiteval.model;

import android.location.Location;

import com.fiteval.R;

import java.util.ArrayList;

/**
 * Created by nader on 11/15/16.
 */

public class RaidList {

    public static ArrayList<Raid> createList() {
        ArrayList<Raid> list = new ArrayList<>();

        Location burrussLoc = new Location("");
        burrussLoc.setLatitude(37.228500);
        burrussLoc.setLongitude(-80.422860);
        Raid burruss = new Raid("Burruss Hall", burrussLoc, R.drawable.burruss);

        Location cafeMekongLoc = new Location("");
        cafeMekongLoc.setLatitude(37.216210);
        cafeMekongLoc.setLongitude(-80.399864);
        Raid cafeMekong = new Raid("Cafe Mekong", cafeMekongLoc, R.drawable.cafemekong);

        Location laneStadiumLoc = new Location("");
        laneStadiumLoc.setLatitude(37.216210);
        laneStadiumLoc.setLongitude(-80.399864);
        Raid laneStadium = new Raid("Lane Stadium", laneStadiumLoc, R.drawable.lanestadium);

        Location mossArtCenterLoc = new Location("");
        mossArtCenterLoc.setLatitude(37.231849);
        mossArtCenterLoc.setLongitude(-80.418066);
        Raid mossArtCenter = new Raid("Moss Art Center", mossArtCenterLoc, R.drawable.mossartcenter);


        Location villageLocation = new Location("");
        villageLocation.setLatitude(37.246264);
        villageLocation.setLongitude(-80.428977);
        Raid village = new Raid("Village", villageLocation, R.drawable.village);

        list.add(burruss);
        list.add(cafeMekong);
        list.add(laneStadium);
        list.add(mossArtCenter);
        list.add(village);

        return list;
    }
}
