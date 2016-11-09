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

    private Context mContext;
    private ImageView mAvatar;
    private TextView mNickName;
    private TextView mItemInfo;
    private Button mButton;

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
        mAvatar = (ImageView) root.findViewById(R.id.fragment_shop_avatar);
        mNickName = (TextView) root.findViewById(R.id.fragment_shop_nickname);
        mItemInfo = (TextView) root.findViewById(R.id.fragment_shop_item_info);
        mButton = (Button) root.findViewById(R.id.fragment_shop_btn);

        applyData();

        return root;
    }

    private void applyData() {
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
