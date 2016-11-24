package com.fiteval.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.data.ShopItemDto;
import com.fiteval.ui.adapter.ShopItemAdapter;
import com.fiteval.ui.custom.NonScrollableGridView;
import com.fiteval.ui.custom.ObservableScrollView;
import com.fiteval.ui.dialog.CommonAlertDialog;
import com.fiteval.ui.viewholder.ShopItemViewHolder;
import com.fiteval.util.MiscUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends BaseFragment implements ObservableScrollView.Callbacks {

    public static final String TAG = MainFragment.class.getName();

    private ObservableScrollView mObservableScrollView;
    private ImageView mAvatar;
    private LinearLayout mStickyView;
    private View mPlaceholderView;
    private NonScrollableGridView mGridView;
    private ShopItemAdapter mAdapter;
    private ProgressDialog mProgress;
    private TextView mGold;

    private List<ShopItemDto> mList;

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
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        mObservableScrollView = (ObservableScrollView) root.findViewById(R.id.fragment_shop_scroll_view);
        mStickyView = (LinearLayout) root.findViewById(R.id.fragment_shop_sticky_header_layout);
        mPlaceholderView = root.findViewById(R.id.fragment_shop_header_placeholder);

        mAvatar = (ImageView) root.findViewById(R.id.fragment_shop_avatar);
        mGridView = (NonScrollableGridView) root.findViewById(R.id.fragment_shop_gridview);

        mGold = (TextView) root.findViewById(R.id.fragment_shop_gold);

        mObservableScrollView.setCallbacks(this);

        mStickyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mObservableScrollView.smoothScrollTo(0, 0);
            }
        });

        mStickyView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mStickyView.getViewTreeObserver().removeOnPreDrawListener(this);
                mPlaceholderView.getLayoutParams().height = mStickyView.getHeight();
                mPlaceholderView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mObservableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(mObservableScrollView.getScrollY());
                    }
                });

        mProgress = new ProgressDialog(mContext);

        mProgress.setMessage("Loading...");
        mProgress.show();
        populateList();
        new GetShopInfoTask().download();

        return root;
    }

    private class GetShopInfoTask {

        private void download() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    syncDataWithServer();
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

    private void populateList() {
        mList = new ArrayList<>();

        // head gears
        mList.add(new ShopItemDto("head_1", ShopItemDto.ItemType.head, "Steel Helm", 100,
                R.drawable.item_head_steel_helm, R.drawable.avatar_head_steel_helm));

        mList.add(new ShopItemDto("head_2", ShopItemDto.ItemType.head, "Viking Helm", 200,
                R.drawable.item_head_viking_helm, R.drawable.avatar_head_viking_helm));

        mList.add(new ShopItemDto("head_3", ShopItemDto.ItemType.head, "Wizard Hat", 300,
                R.drawable.item_head_wizard_hat, R.drawable.avatar_head_wizard_hat));

        // body armors
        mList.add(new ShopItemDto("body_1", ShopItemDto.ItemType.body, "Steel Armor", 100,
                R.drawable.item_body_steel_armor, R.drawable.avatar_body_steel_armor));

        mList.add(new ShopItemDto("body_2", ShopItemDto.ItemType.body, "Viking Armor", 200,
                R.drawable.item_body_viking_armor, R.drawable.avatar_body_viking_armor));

        mList.add(new ShopItemDto("body_3", ShopItemDto.ItemType.body, "Wizard Clock", 300,
                R.drawable.item_body_wizard_cloak, R.drawable.avatar_body_wizard_cloak));

        // weapons
        mList.add(new ShopItemDto("weapon_1", ShopItemDto.ItemType.weapon, "Great Sword", 100,
                R.drawable.item_weapon_great_sword, R.drawable.avatar_weapon_great_sword));

        mList.add(new ShopItemDto("weapon_2", ShopItemDto.ItemType.weapon, "Battle Axe", 200,
                R.drawable.item_weapon_battle_axe, R.drawable.avatar_weapon_battle_axe));

        mList.add(new ShopItemDto("weapon_3", ShopItemDto.ItemType.weapon, "Oak Staff", 300,
                R.drawable.item_weapon_oak_staff, R.drawable.avatar_weapon_oak_staff));

        // foot gears
        mList.add(new ShopItemDto("foot_1", ShopItemDto.ItemType.foot, "Steel Boots", 100,
                R.drawable.item_foot_steel_boots, R.drawable.avatar_foot_steel_boots));

        mList.add(new ShopItemDto("foot_2", ShopItemDto.ItemType.foot, "Viking Boots", 200,
                R.drawable.item_foot_viking_boots, R.drawable.avatar_foot_viking_boots));

        mList.add(new ShopItemDto("foot_3", ShopItemDto.ItemType.foot, "Wizard Boots", 300,
                R.drawable.item_foot_wizard_boots, R.drawable.avatar_foot_wizard_boots));

        mAdapter = new ShopItemAdapter(mContext, mList);

        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ShopItemDto dto = (ShopItemDto) mAdapter.getItem(position);

                if (dto.isPurchased) {
                    // Take item off ---------------------------------------------------------------
                    if (dto.isWearing) {
                        mAlertDialog.setCustomMessage(String.format("Take off '%s' ?", dto.itemName));
                        mAlertDialog.setOnButtonClickedListener(new CommonAlertDialog.onButtonClickedListener() {
                            @Override
                            public void onPositive() {
                                // sync with firebase here

                                dto.isWearing = false;
                                switch (dto.type) {
                                    case head:
                                        mUserDto.headItemId = null;
                                        break;
                                    case body:
                                        mUserDto.bodyItemId = null;
                                        break;
                                    case weapon:
                                        mUserDto.weaponItemId = null;
                                        break;
                                    case foot:
                                        mUserDto.footItemId = null;
                                        break;
                                }

                                mAdapter.notifyDataSetChanged();
                                drawAvatar();

                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }

                            @Override
                            public void onNegative() {
                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }
                        });
                        mAlertDialog.show();
                    }
                    // Wear item ---------------------------------------------------------------
                    else {
                        mAlertDialog.setCustomMessage(String.format("Wear '%s' ?", dto.itemName));
                        mAlertDialog.setOnButtonClickedListener(new CommonAlertDialog.onButtonClickedListener() {
                            @Override
                            public void onPositive() {
                                // sync with firebase here

                                // handle previously wearing item if exists
                                switch (dto.type) {
                                    case head:
                                        if (mUserDto.headItemId != null) {
                                            findItemById(mUserDto.headItemId).isWearing = false;
                                        }
                                        mUserDto.headItemId = dto.id;
                                        break;

                                    case body:
                                        if (mUserDto.bodyItemId != null) {
                                            findItemById(mUserDto.bodyItemId).isWearing = false;
                                        }
                                        mUserDto.bodyItemId = dto.id;

                                        break;
                                    case weapon:
                                        if (mUserDto.weaponItemId != null) {
                                            findItemById(mUserDto.weaponItemId).isWearing = false;
                                        }
                                        mUserDto.weaponItemId = dto.id;
                                        break;

                                    case foot:
                                        if (mUserDto.footItemId != null) {
                                            findItemById(mUserDto.footItemId).isWearing = false;
                                        }
                                        mUserDto.footItemId = dto.id;

                                        break;
                                }

                                dto.isWearing = true;
                                mAdapter.notifyDataSetChanged();
                                drawAvatar();

                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }

                            @Override
                            public void onNegative() {
                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }
                        });
                        mAlertDialog.show();
                    }
                }
                // Buy item ------------------------------------------------------------------------
                else {
                    mAlertDialog.setCustomMessage(String.format("Buy '%s' for %d gold ?", dto.itemName, dto.cost));
                    mAlertDialog.setOnButtonClickedListener(new CommonAlertDialog.onButtonClickedListener() {
                        @Override
                        public void onPositive() {

                            if (mAlertDialog.isShowing()) {
                                mAlertDialog.dismiss();
                            }

                            if (mUserDto.gold >= 0 && dto.cost >= 0 && mUserDto.gold >= dto.cost) {
                                mUserDto.gold -= dto.cost;
                                mGold.setText(NumberFormat.getIntegerInstance().format(mUserDto.gold ));
                                // sync with firebase here

                                dto.isPurchased = true;
                                dto.isWearing = false;

                                mAdapter.notifyDataSetChanged();
                            }
                            else {
                                mDialog.show("Insufficient fund!\nJoin raids for more gold!");
                            }
                        }

                        @Override
                        public void onNegative() {
                            if (mAlertDialog.isShowing()) {
                                mAlertDialog.dismiss();
                            }
                        }
                    });
                    mAlertDialog.show();
                }
            }
        });
    }

    /**
     *
     */
    private void syncDataWithServer() {
        mGold.setText(NumberFormat.getIntegerInstance().format(mUserDto.gold ));

        mList.get(0).isPurchased = true;
        mList.get(1).isPurchased = false;
        mList.get(2).isPurchased = false;
        mList.get(3).isPurchased = true;
        mList.get(4).isPurchased = true;
        mList.get(5).isPurchased = false;
        mList.get(6).isPurchased = true;
        mList.get(7).isPurchased = false;
        mList.get(8).isPurchased = false;
        mList.get(9).isPurchased = true;
        mList.get(10).isPurchased = false;
        mList.get(11).isPurchased = false;

        mList.get(0).isWearing = true;
        mList.get(1).isWearing = false;
        mList.get(2).isWearing = false;
        mList.get(3).isWearing = true;
        mList.get(4).isWearing = false;
        mList.get(5).isWearing = false;
        mList.get(6).isWearing = true;
        mList.get(7).isWearing = false;
        mList.get(8).isWearing = false;
        mList.get(9).isWearing = true;
        mList.get(10).isWearing = false;
        mList.get(11).isWearing = false;

        mList.get(0).isDataLoaded = true;
        mList.get(1).isDataLoaded = true;
        mList.get(2).isDataLoaded = true;
        mList.get(3).isDataLoaded = true;
        mList.get(4).isDataLoaded = true;
        mList.get(5).isDataLoaded = true;
        mList.get(6).isDataLoaded = true;
        mList.get(7).isDataLoaded = true;
        mList.get(8).isDataLoaded = true;
        mList.get(9).isDataLoaded = true;
        mList.get(10).isDataLoaded = true;
        mList.get(11).isDataLoaded = true;

        mAdapter.notifyDataSetChanged();

        mUserDto.headItemId = "head_1";
        mUserDto.bodyItemId = "body_1";
        mUserDto.weaponItemId = "weapon_1";
        mUserDto.footItemId = "foot_1";

        drawAvatar();
    }

    /**
     * @param id
     * @return
     */
    private ShopItemDto findItemById(String id) {
        if (id != null) {
            for (ShopItemDto dto : mList) {
                if (dto.id.equals(id)) {
                    return dto;
                }
            }
        }
        return null;
    }

    private void drawAvatar() {
        ShopItemDto headItem = findItemById(mUserDto.headItemId);
        ShopItemDto bodyItem = findItemById(mUserDto.bodyItemId);
        ShopItemDto weaponItem = findItemById(mUserDto.weaponItemId);
        ShopItemDto footItem = findItemById(mUserDto.footItemId);

        int base = R.drawable.avatar_base;
        int head = (headItem != null) ? headItem.wearImage : R.drawable.avatar_transparent;
        int body = (bodyItem != null) ? bodyItem.wearImage : R.drawable.avatar_transparent;
        int weapon = (weaponItem != null) ? weaponItem.wearImage : R.drawable.avatar_transparent;
        int foot = (footItem != null) ? footItem.wearImage : R.drawable.avatar_transparent;
        int hand = R.drawable.avatar_hand;

        Drawable[] layers = new Drawable[6];
        layers[0] = ContextCompat.getDrawable(mContext, base);
        layers[1] = ContextCompat.getDrawable(mContext, body);
        layers[2] = ContextCompat.getDrawable(mContext, head);
        layers[3] = ContextCompat.getDrawable(mContext, weapon);
        layers[4] = ContextCompat.getDrawable(mContext, hand);
        layers[5] = ContextCompat.getDrawable(mContext, foot);

        mAvatar.setImageDrawable(new LayerDrawable(layers));
    }

    @Override
    public void onScrollChanged(int scrollY) {
        mStickyView.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    private Callback mCallback;

    public interface Callback {
        void updateToolbarTitle(String title);
    }
}
