package com.fiteval.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fiteval.R;
import com.fiteval.config.Config;
import com.fiteval.ui.activity.MainActivity;
import com.fiteval.ui.adapter.RaidListAdapter;
import com.fiteval.ui.custom.NonScrollableGridView;
import com.fiteval.ui.custom.NonScrollableListView;
import com.fiteval.ui.item.RaidListItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class RaidFragment extends BaseFragment {

    public static final String TAG = RaidFragment.class.getName();

    private MapView mMapView;
    private GoogleMap googleMap;
    private NonScrollableListView mListView;
    private ProgressDialog mProgress;

    private RaidListAdapter mAdapter;
    private List<RaidListItem> mList;
    private Marker lastMarker;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_raid, container, false);

        mProgress = new ProgressDialog(mContext);
        mProgress.setMessage("Loading...");
        mProgress.show();

        mMapView = (MapView) root.findViewById(R.id.fragment_raid_map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getGoogleMap();

        mListView = (NonScrollableListView) root.findViewById(R.id.fragment_raid_listview);

        populateList();

        new GetRaidInfoTask().download();

        return root;
    }

    private class GetRaidInfoTask {

        private void download() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    synDataWithServer();
                    mProgress.dismiss();
                }
            }, 1000);
        }

        class AsyncDownloadTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }
        }
    }

    private void synDataWithServer() {

    }

    private void populateList() {
        mList = new ArrayList<>();

        mList.add(new RaidListItem("Burruss Hall",
                "Burruss Hall is the main administration building on campus." +
                        "\nMake a visit here to see the beautiful drill field!",
                "12/2/2016", "12/3/2016", R.drawable.burruss, 37.228500, -80.422860, 50));

        mList.add(new RaidListItem("Lane Stadium",
                "Lane Stadium is home of our proud Hokies.\nMake a visit here and support our athletes!",
                "12/2/2016", "12/3/2016", R.drawable.lanestadium, 37.216210, -80.399864, 50));

        mList.add(new RaidListItem("Moss Art Center",
                "The Moss Arts Center is creating a thriving creative community " +
                        "fueled by inspiration.\nMake a visit here for exhibitions.",
                "12/2/2016", "12/3/2016", R.drawable.mossartcenter, 37.231849, -80.418066, 50));

        mList.add(new RaidListItem("Cafe Mekong",
                "Cafe Mekong is one of the most popular restaurants in Blacksburg.\n" +
                        "Make a visit here for delicious Vietnamese and Thai cusine!",
                "12/2/2016", "12/3/2016", R.drawable.cafemekong, 37.216210, -80.399864, 50));

        mAdapter = new RaidListAdapter(mContext, mList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final RaidListItem item = (RaidListItem) mAdapter.getItem(position);

                if (lastMarker != null) {
                    lastMarker.remove();
                    lastMarker = null;
                }

                LatLng place = new LatLng(item.latitude, item.longitude);
                lastMarker = googleMap.addMarker(new MarkerOptions().position(place).title(item.title).snippet("Make a visit here!"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(place).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

    }


    private void getGoogleMap() {
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                ((MainActivity) mContext).setCallbackOnRequestPermission(new MainActivity.OnRequestPermissionResultListener() {
                    @Override
                    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
                        if (requestCode == Config.REQUEST_PERMISSION_LOCATION) {
                            if (grantResults.length > 0
                                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                // permission granted
                                getGoogleMap();
                            }
                        }
                    }
                });

                if (ContextCompat.checkSelfPermission(mContext,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                            Config.REQUEST_PERMISSION_LOCATION);
                } else {
                    // executed when permission is granted
                    googleMap = mMap;
                    googleMap.setMyLocationEnabled(true);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mCallback.updateToolbarTitle("Raid");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    private Callback mCallback;

    public interface Callback {

        void updateToolbarTitle(String title);
    }
}
