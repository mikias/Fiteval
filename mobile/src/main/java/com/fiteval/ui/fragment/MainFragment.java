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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiteval.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private ImageView mAvatar;
    private TextView mBpm;
    private TextView mStep;
    private TextView mLevel;
    private TextView mExp;
    private TextView mGold;

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
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = getActivity();
        mAvatar = (ImageView) root.findViewById(R.id.fragment_main_avatar);
        mBpm = (TextView) root.findViewById(R.id.fragment_main_bpm);
        mStep = (TextView) root.findViewById(R.id.fragment_main_step);
        mLevel = (TextView) root.findViewById(R.id.fragment_main_level);
        mExp = (TextView) root.findViewById(R.id.fragment_main_exp);
        mGold = (TextView) root.findViewById(R.id.fragment_main_gold);

        applyAvatarImage();
        applyData();

        return root;
    }

    private void applyAvatarImage() {
        mAvatar.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.knight));
    }
    private void applyData() {
        mBpm.setText("105");
<<<<<<< HEAD
        mStep.setText(Integer.toString(MainActivity.knight.steps));
        mLevel.setText(Integer.toString(MainActivity.knight.getmLevel()));
        mExp.setText(MainActivity.knight.getmExperience() + "/" +
                MainActivity.knight.getmExperienceRemaining());
        mGold.setText(Integer.toString(MainActivity.knight.getmGold()));
=======
        mStep.setText("1788");
        mLevel.setText("17");
        mExp.setText("1020/1500");
        mGold.setText("46007");
>>>>>>> origin/henry-patch
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
