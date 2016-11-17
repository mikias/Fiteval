package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.ui.activity.MainActivity;

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private ImageView mAvatar;
    private TextView mBpm;
    private TextView mStep;
    private TextView mLevel;
    private TextView mExp;
    private TextView mGold;
    private Callback mCallback;

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
        mStep.setText(Integer.toString(MainActivity.knight.steps));
        mLevel.setText(Integer.toString(MainActivity.knight.getmLevel()));
        mExp.setText(MainActivity.knight.getmExperience() + "/" +
                MainActivity.knight.getmExperienceRemaining());
        mGold.setText(MainActivity.knight.getmGold());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    public interface Callback {

        void updateToolbarTitle(String title);
    }
}
