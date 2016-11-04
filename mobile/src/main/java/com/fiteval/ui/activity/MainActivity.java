package com.fiteval.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.fiteval.R;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.ui.fragment.MainFragment;
import com.fiteval.ui.fragment.NavigationFragment;
import com.fiteval.util.MiscUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        DrawerLayout.DrawerListener,
        NavigationFragment.Callback {

    private final static int mContainerFragment = R.id.activity_main_container;

    private NavigationFragment mNavigationFragment;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable mMaterialMenu;
    private Toolbar mToolbar;
    private MiscUtil mUtil;
    private SimpleAlertDialog mDialog;

    // -------------------------------------------------------
    private Context mContext;
    private FragmentManager mFragManager;
    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        mFragManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        mUtil = new MiscUtil(this);
        mDialog = new SimpleAlertDialog(this);
        initToolbar();

        mFragment = new MainFragment();
        mToolbar.setTitle("Avatar");
        switchFragment(MainFragment.TAG);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initToolbar();
    }

    private void initView() {
        if (mNavigationFragment == null) {
            mNavigationFragment = (NavigationFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.common_navigation_drawer);

            if (mNavigationFragment == null) {
                mNavigationFragment = new NavigationFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.common_navigation_drawer, mNavigationFragment)
                    .commit();
        }

        if (mDrawerLayout == null) {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer_layout);
            mDrawerLayout.setFocusable(false);
            mDrawerLayout.setVisibility(View.VISIBLE);
            mDrawerLayout.setDrawerListener(this);
            mDrawerLayout.setScrimColor(ContextCompat.getColor(this, android.R.color.transparent));
            mNavigationFragment.setUp(mDrawerLayout);
        }
    }

    private void initToolbar() {
        if (mToolbar == null) {
            getToolbar();
        }
        mMaterialMenu = new MaterialMenuDrawable(MainActivity.this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenu);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNavigationFragment != null) {
                    if (mNavigationFragment.isDrawerOpen()) {
                        mNavigationFragment.closeDrawer();
                        mMaterialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                    } else {
                        mNavigationFragment.openDrawer();
                        mMaterialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                    }
                }
            }
        });
        String title = (String) mToolbar.getTitle();
        if (title != null) {
            // DataUtil.getSpannableString(title, true);
            mToolbar.setTitle(title);
        }
        setSupportActionBar(mToolbar);
    }

    protected Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        }
        return mToolbar;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (mNavigationFragment != null) {
            mMaterialMenu.setTransformationOffset(
                    MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                    mNavigationFragment.isDrawerOpen() ? 2 - slideOffset : slideOffset
            );
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        if (mNavigationFragment != null) {
            mNavigationFragment.setDrawerOpen(true);
        }
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        if (mNavigationFragment != null) {
            mNavigationFragment.setDrawerOpen(false);
            mNavigationFragment.setOpenedByBackBtn(false);
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if (newState == DrawerLayout.STATE_IDLE) {
            if (mNavigationFragment.isDrawerOpen()) {
                mMaterialMenu.setIconState(MaterialMenuDrawable.IconState.ARROW);
            } else {
                mMaterialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // =============================================================================================
    @Override
    public void navigationListOnClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_navigation_drawer_btn_main:
                mUtil.toastCenter("MAIN Clicked");
                mToolbar.setTitle("Avatar");
                break;

            case R.id.fragment_navigation_drawer_btn_raid:
                mUtil.toastCenter("RAID Clicked");
                mToolbar.setTitle("RaidFragment");
                break;

            case R.id.fragment_navigation_drawer_btn_leaderboard:
                mToolbar.setTitle("Leaderboard");
                mUtil.toastCenter("LEADERBOARD Clicked");
                break;

            case R.id.fragment_navigation_drawer_btn_shop:
                mUtil.toastCenter("SHOP Clicked");
                mToolbar.setTitle("Shop");
                break;
        }
    }

    private void switchFragment(String tag) {
        mFragManager.beginTransaction()
                .replace(mContainerFragment, mFragment, tag)
                .addToBackStack(tag)
                .commit();
    }
}