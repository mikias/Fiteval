package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiteval.R;

/**
 * Created by Henry Moon on 10/29/2016.
 */

public class NavigationFragment extends Fragment implements
        View.OnClickListener,
        View.OnTouchListener {

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private Context mContext;

    private LinearLayout mBtnMain;
    private LinearLayout mBtnRaid;
    private LinearLayout mBtnLeader;
    private LinearLayout mBtnShop;

    private TextView mTvMain;
    private TextView mTvRaid;
    private TextView mTvLeader;
    private TextView mTvShop;

    private boolean isDrawerOpen;
    private boolean isOpenByBackBtn;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isDrawerOpen", isDrawerOpen);
        outState.putBoolean("isOpenByBackBtn", isOpenByBackBtn);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isDrawerOpen = savedInstanceState.getBoolean("isDrawerOpen");
            isOpenByBackBtn = savedInstanceState.getBoolean("isOpenByBackBtn");
            mFragmentContainerView = getActivity().findViewById(R.id.common_navigation_drawer);
        }

        View root = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mBtnMain = (LinearLayout) root.findViewById(R.id.fragment_navigation_drawer_btn_main);
        mBtnRaid = (LinearLayout) root.findViewById(R.id.fragment_navigation_drawer_btn_raid);
        mBtnLeader = (LinearLayout) root.findViewById(R.id.fragment_navigation_drawer_btn_leaderboard);
        mBtnShop = (LinearLayout) root.findViewById(R.id.fragment_navigation_drawer_btn_shop);

        mTvMain = (TextView) root.findViewById(R.id.fragment_navigation_drawer_tv_main);
        mTvRaid = (TextView) root.findViewById(R.id.fragment_navigation_drawer_tv_raid);
        mTvLeader = (TextView) root.findViewById(R.id.fragment_navigation_drawer_tv_leaderboard);
        mTvShop = (TextView) root.findViewById(R.id.fragment_navigation_drawer_tv_shop);

        mBtnMain.setOnClickListener(this);
        mBtnMain.setOnTouchListener(this);

        mBtnRaid.setOnClickListener(this);
        mBtnRaid.setOnTouchListener(this);

        mBtnLeader.setOnClickListener(this);
        mBtnLeader.setOnTouchListener(this);

        mBtnShop.setOnClickListener(this);
        mBtnShop.setOnTouchListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.navigationListOnClick(v);
        }

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.fragment_navigation_drawer_btn_main:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    mBtnMain.setBackgroundResource(R.color.fragment_navigation_drawer_background_pressed);
                    mTvMain.setTextColor(ContextCompat.getColor(mContext, R.color.fragment_navigation_drawer_txt_pressed));
                } else if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
                    mBtnMain.setBackgroundResource(R.color.fragment_navigation_drawer_background_default);
                    mTvMain.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                }
                break;
            case R.id.fragment_navigation_drawer_btn_raid:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    mBtnRaid.setBackgroundResource(R.color.fragment_navigation_drawer_background_pressed);
                    mTvRaid.setTextColor(ContextCompat.getColor(mContext, R.color.fragment_navigation_drawer_txt_pressed));
                } else if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
                    mBtnRaid.setBackgroundResource(R.color.fragment_navigation_drawer_background_default);
                    mTvRaid.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                }
                break;
            case R.id.fragment_navigation_drawer_btn_leaderboard:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    mBtnLeader.setBackgroundResource(R.color.fragment_navigation_drawer_background_pressed);
                    mTvLeader.setTextColor(ContextCompat.getColor(mContext, R.color.fragment_navigation_drawer_txt_pressed));
                } else if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
                    mBtnLeader.setBackgroundResource(R.color.fragment_navigation_drawer_background_default);
                    mTvLeader.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                }
                break;
            case R.id.fragment_navigation_drawer_btn_shop:
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    mBtnShop.setBackgroundResource(R.color.fragment_navigation_drawer_background_pressed);
                    mTvShop.setTextColor(ContextCompat.getColor(mContext, R.color.fragment_navigation_drawer_txt_pressed));
                } else if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
                    mBtnShop.setBackgroundResource(R.color.fragment_navigation_drawer_background_default);
                    mTvShop.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                }
                break;
        }
        return false;
    }

    public boolean isDrawerOpen() {
        return isDrawerOpen;
    }

    public boolean isOpenByBackBtn() {
        return isOpenByBackBtn;
    }

    public void setDrawerOpen(boolean state) {
        this.isDrawerOpen = state;
    }

    public void setOpenedByBackBtn(boolean state) {
        this.isOpenByBackBtn = state;
    }

    public void openDrawer() {
        isDrawerOpen = true;
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        isDrawerOpen = false;
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    public void setUp(DrawerLayout drawerLayout) {
        if (mDrawerLayout == null) {
            mDrawerLayout = drawerLayout;
        }
    }


    private Callback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
        mFragmentContainerView = getActivity().findViewById(R.id.common_navigation_drawer);
    }

    public interface Callback {
        void navigationListOnClick(View v);
    }
}
