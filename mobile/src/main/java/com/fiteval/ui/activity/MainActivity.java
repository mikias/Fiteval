package com.fiteval.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.fiteval.R;
import com.fiteval.controller.ExperienceService;
import com.fiteval.controller.HeartReaderService;
import com.fiteval.model.Equipment;
import com.fiteval.model.InvSlots;
import com.fiteval.model.Inventory;
import com.fiteval.model.Knight;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.ui.fragment.LeaderboardFragment;
import com.fiteval.ui.fragment.MainFragment;
import com.fiteval.ui.fragment.NavigationFragment;
import com.fiteval.ui.fragment.RaidFragment;
import com.fiteval.ui.fragment.ShopFragment;
import com.fiteval.util.MiscUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        DrawerLayout.DrawerListener,
        NavigationFragment.Callback,
        LeaderboardFragment.Callback,
        MainFragment.Callback,
        RaidFragment.Callback,
        ShopFragment.Callback {

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
    private Intent mServiceIntent;

    // -------------------------------------------------------
    public static Knight knight;

    public int mHeartRate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button mSignUp = (Button) findViewById(R.id.signup);

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


        //TODO: check if a knight save exists or if the user exists and pull the info
        knight = new Knight(2000, 10000, new Inventory(new ArrayList<Equipment>()));
        loadItems();

        mServiceIntent = new Intent(this, ExperienceService.class);
        startService(mServiceIntent);

        mServiceIntent = new Intent(this, HeartReaderService.class);
        startService(mServiceIntent);

    }


    //handles heart reading
    @Override
    protected void onResume() {
        Log.d("Resume", "resumed");
        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                // message from API client! message from wear! The contents is the heartbeat.
//                //textView.setText(Integer.toString(msg.what));
//                mHeartRate = msg.what;
//                Log.d("Handler", "Heartrate read");
//                Fragment currentFrag = mFragManager.findFragmentById(mContainerFragment);
//                if (currentFrag instanceof MainFragment) {
//                    TextView tv = (TextView) findViewById(R.id.fragment_main_bpm);
//                    Log.d("Handler", tv.getText().toString());
//                    tv.setText(mHeartRate);
//                }
//            }
        };
        // register our handler with the HeartReader. This ensures we get messages whenever the service receives something.
        HeartReaderService.setHandler(handler);
        Log.d("Handler", "handler set");
        super.onResume();
    }

    //handles heart reading
    @Override
    protected void onPause() {
        // unregister our handler so the service does not need to send its messages anywhere.
        HeartReaderService.setHandler(null);
        super.onPause();
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
        Fragment currentFrag;

        switch (v.getId()) {
            case R.id.fragment_navigation_drawer_btn_main:
                mToolbar.setTitle("Avatar");
                currentFrag = mFragManager.findFragmentById(mContainerFragment);
                if (currentFrag instanceof MainFragment) {
                    forceUpdateView(currentFrag);
                } else {
                    clearBackStack();
                    mFragment = new MainFragment();
                    switchFragment(MainFragment.TAG);
                }
                break;

            case R.id.fragment_navigation_drawer_btn_raid:
                mUtil.toastCenter("Raid fragment is not implemented yet");
                mToolbar.setTitle("RaidFragment");
                break;

            case R.id.fragment_navigation_drawer_btn_leaderboard:
                currentFrag = mFragManager.findFragmentById(mContainerFragment);
                if (currentFrag instanceof LeaderboardFragment) {
                    forceUpdateView(currentFrag);
                } else {
                    clearBackStack();
                    mFragment = new LeaderboardFragment();
                    switchFragment(LeaderboardFragment.TAG);
                }
                break;

            case R.id.fragment_navigation_drawer_btn_shop:
                mToolbar.setTitle("Shop");
                currentFrag = mFragManager.findFragmentById(mContainerFragment);
                if (currentFrag instanceof ShopFragment) {
                    forceUpdateView(currentFrag);
                } else {
                    clearBackStack();
                    mFragment = new ShopFragment();
                    switchFragment(ShopFragment.TAG);
                }
                break;
        }
    }

    private void switchFragment(String tag) {
        mFragManager.beginTransaction()
                .replace(mContainerFragment, mFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void loadItems() {
        Equipment wizHat, steelHelm, vikingHelm;
        Equipment wizCloak, steelPlate, vikingArmor;
        Equipment staff, sword, axe;

        //Equipment statements require these params:
        //boolean equipped, boolean purchased, String name, int cost, InvSlots slot
        wizHat = new Equipment(false, false, "Wizard Hat", 100, InvSlots.HELMET);
        steelHelm = new Equipment(false, false, "Steel Helmet", 300, InvSlots.HELMET);
        vikingHelm = new Equipment(false, false, "Viking Helmet", 1000, InvSlots.HELMET);

        wizCloak = new Equipment(false, false, "Wizard Cloak", 200, InvSlots.ARMOR);
        steelPlate = new Equipment(false, false, "Steel Platebody", 650, InvSlots.ARMOR);
        vikingArmor = new Equipment(false, false, "Viking Armor", 1500, InvSlots.ARMOR);

        staff = new Equipment(false, false, "Oak Staff", 50, InvSlots.WEAPON);
        sword = new Equipment(false, false, "Iron Sword", 400, InvSlots.WEAPON);
        axe = new Equipment(false, false, "Battle Axe", 1000, InvSlots.WEAPON);

        //TODO load if items are purchased or equipped

        knight.getmInv().addItem(wizHat).addItem(steelHelm).addItem(vikingHelm);
        knight.getmInv().addItem(wizCloak).addItem(steelPlate).addItem(vikingArmor);
        knight.getmInv().addItem(staff).addItem(sword).addItem(axe);
    }

    private void clearBackStack() {
        try {
            String firstTag = mFragManager.getBackStackEntryAt(0).getName();
            mFragManager.popBackStackImmediate(firstTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (IndexOutOfBoundsException ie) {
            ie.printStackTrace();
        } catch (NullPointerException ne) {
            Log.e("clearBackStack", "getBackStackEntry calling on null stack");
            ne.printStackTrace();
        }
    }

    private void forceUpdateView(Fragment fragment) {
        mFragManager.beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }

    @Override
    public void updateToolbarTitle(String title) {
        Toolbar toolbar = getToolbar();
        CharSequence oldTitle = toolbar.getTitle();
        if (oldTitle == null || !oldTitle.equals(title)) {
            toolbar.setTitle(title);
        }
    }

}