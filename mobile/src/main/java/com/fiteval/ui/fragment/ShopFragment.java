package com.fiteval.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
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

public class ShopFragment extends Fragment implements ObservableScrollView.Callbacks {

    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private ObservableScrollView mObservableScrollView;
    private ImageView mAvatar;
    private LinearLayout mStickyView;
    private View mPlaceholderView;
    private NonScrollableGridView mGridView;
    private ShopItemAdapter mAdapter;
    private ProgressDialog mProgress;
    private TextView mGold;

    private List<ShopItemDto> mList;

    private CommonAlertDialog mAlertDialog;
    private MiscUtil mUtils;

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

        mContext = getActivity();
        mUtils = new MiscUtil(mContext);
        mAlertDialog = new CommonAlertDialog(mContext);
        mAlertDialog.setCancelable(true);

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
                    mAvatar.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.knight));
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
        mList.add(new ShopItemDto("helm_1", "Steel Helm", 100,
                ContextCompat.getDrawable(mContext, R.drawable.box), true, true));
        mList.add(new ShopItemDto("helm_2", "Viking Helmet", 200,
                ContextCompat.getDrawable(mContext, R.drawable.box), false, false));
        mList.add(new ShopItemDto("helm_3", "Wizard Hat", 300,
                ContextCompat.getDrawable(mContext, R.drawable.box), true, false));

        mList.add(new ShopItemDto("body_1", "Steel Armor", 100,
                ContextCompat.getDrawable(mContext, R.drawable.box), true, true));
        mList.add(new ShopItemDto("body_2", "Viking Armor", 200,
                ContextCompat.getDrawable(mContext, R.drawable.box), true, false));
        mList.add(new ShopItemDto("body_3", "Wizard Clock", 300,
                ContextCompat.getDrawable(mContext, R.drawable.box), false, false));

        mList.add(new ShopItemDto("weapon_1", "Battle Axe", 100,
                ContextCompat.getDrawable(mContext, R.drawable.box), true, true));
        mList.add(new ShopItemDto("weapon_2", "Great Sword", 200,
                ContextCompat.getDrawable(mContext, R.drawable.box)));
        mList.add(new ShopItemDto("weapon_3", "Oak Staff", 300,
                ContextCompat.getDrawable(mContext, R.drawable.box)));

        mAdapter = new ShopItemAdapter(mContext, mList);

        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ShopItemDto dto = (ShopItemDto) mAdapter.getItem(position);
                final ShopItemViewHolder viewHolder = (ShopItemViewHolder) view.getTag();

                if (dto.isPurchased) {
                    // Take item off ---------------------------------------------------------------
                    if (dto.isWearing) {
                        mAlertDialog.setCustomMessage(String.format("Take off '%s' ?", dto.itemName));
                        mAlertDialog.setOnButtonClickedListener(new CommonAlertDialog.onButtonClickedListener() {
                            @Override
                            public void onPositive() {
                                dto.isWearing = false;
                                viewHolder.priceLayout.setVisibility(View.GONE);
                                viewHolder.takeOffLayout.setVisibility(View.GONE);
                                viewHolder.wearLayout.setVisibility(View.VISIBLE);

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
                                dto.isWearing = true;
                                viewHolder.priceLayout.setVisibility(View.GONE);
                                viewHolder.wearLayout.setVisibility(View.GONE);
                                viewHolder.takeOffLayout.setVisibility(View.VISIBLE);

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
                            dto.isPurchased = true;
                            dto.isWearing = false;
                            viewHolder.priceLayout.setVisibility(View.GONE);
                            viewHolder.takeOffLayout.setVisibility(View.GONE);
                            viewHolder.wearLayout.setVisibility(View.VISIBLE);

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
        });
    }

    private void syncDataWithServer() {
        int gold = 1000;
        mGold.setText(NumberFormat.getIntegerInstance().format(gold));
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
