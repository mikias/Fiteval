package com.fiteval.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiteval.R;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    private ImageView mIVWizardHat;
    private ImageView mIVSteelHelm;
    private ImageView mIVVikingHelm;

    private ImageView mIVWizardCloak;
    private ImageView mIVSteelPlate;
    private ImageView mIVVikingArmor;

    private ImageView mIVOakStaff;
    private ImageView mIVSword;
    private ImageView mIVBattleAxe;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mIVWizardHat = (ImageView) container.findViewById(R.id.iv_wizard_hat);
        mIVSteelHelm = (ImageView) container.findViewById(R.id.iv_steel_helm);
        mIVVikingHelm = (ImageView) container.findViewById(R.id.iv_viking_helmet);

        mIVWizardCloak = (ImageView) container.findViewById(R.id.iv_wizard_cloak);
        mIVSteelPlate = (ImageView) container.findViewById(R.id.iv_steelplate);
        mIVVikingArmor = (ImageView) container.findViewById(R.id.iv_viking_armor);

        mIVOakStaff = (ImageView) container.findViewById(R.id.iv_oakstaff);
        mIVSword = (ImageView) container.findViewById(R.id.iv_sword);
        mIVBattleAxe = (ImageView) container.findViewById(R.id.iv_battle_axe);

        //Change items
        mIVWizardHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mIVSteelHelm.setVisibility(View.INVISIBLE);
                mIVVikingHelm.setVisibility(View.INVISIBLE);
                mIVWizardHat.setVisibility(View.VISIBLE);
            }
        });

        mIVSteelHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mIVVikingHelm.setVisibility(View.INVISIBLE);
                mIVWizardHat.setVisibility(View.INVISIBLE);
                mIVSteelHelm.setVisibility(View.VISIBLE);
            }
        });

        mIVVikingHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mIVWizardHat.setVisibility(View.INVISIBLE);
                mIVSteelHelm.setVisibility(View.INVISIBLE);
                mIVVikingHelm.setVisibility(View.VISIBLE);
            }
        });





        return view;
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
