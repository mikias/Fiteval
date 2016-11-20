package com.fiteval.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.data.ShopItemDto;
import com.fiteval.ui.adapter.ShopItemAdapter;
import com.fiteval.ui.custom.NonScrollableGridView;
import com.fiteval.ui.custom.ObservableScrollView;
import com.fiteval.util.MiscUtil;

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

    private Button mBuyBtn;
    private Button mWearBtn;
    private Button mTakeOffBtn;

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

        mObservableScrollView = (ObservableScrollView) root.findViewById(R.id.fragment_shop_scroll_view);
        mStickyView = (LinearLayout) root.findViewById(R.id.fragment_shop_sticky_header_layout);
        mPlaceholderView = root.findViewById(R.id.fragment_shop_header_placeholder);

        mAvatar = (ImageView) root.findViewById(R.id.fragment_shop_avatar);
        mGridView = (NonScrollableGridView) root.findViewById(R.id.fragment_shop_gridview);

        mBuyBtn = (Button) root.findViewById(R.id.fragment_shop_buy_btn);
        mWearBtn = (Button) root.findViewById(R.id.fragment_shop_wear_btn);
        mTakeOffBtn = (Button) root.findViewById(R.id.fragment_shop_takeoff_btn);

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.toastCenter("Buying");
            }
        });

        mWearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.toastCenter("Wearing");
            }
        });

        mTakeOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.toastCenter("Taking off");
            }
        });

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
                    mProgress.dismiss();
                    mAvatar.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.knight));
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
        List<ShopItemDto> list = new ArrayList<>();
        list.add(new ShopItemDto("Item_1", 100));
        list.add(new ShopItemDto("Item_2", 200));
        list.add(new ShopItemDto("Item_3", 300));
        list.add(new ShopItemDto("Item_4", 400));
        list.add(new ShopItemDto("Item_5", 500));
        list.add(new ShopItemDto("Item_6", 600));
        list.add(new ShopItemDto("Item_7", 700));
        list.add(new ShopItemDto("Item_8", 800));
        list.add(new ShopItemDto("Item_9", 900));
        list.add(new ShopItemDto("Item_10", 900));
        list.add(new ShopItemDto("Item_11", 900));
        list.add(new ShopItemDto("Item_12", 900));

        mAdapter = new ShopItemAdapter(mContext, list);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mUtils.toastCenter("Position: " + position);
            }
        });
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

    private Callback mCallback;
    public interface Callback {
        void updateToolbarTitle(String title);
    }
}
