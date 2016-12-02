package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.ui.custom.AutoResizeTextView;

import java.text.NumberFormat;

public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private ImageView mAvatar;
    private TextView mBpm;
    private AutoResizeTextView mStep;
    private AutoResizeTextView mLevel;
    private ProgressBar mExpProgress;
    private TextView mStepsLeft;
    private TextView mStepsStatus;
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
        mStep = (AutoResizeTextView) root.findViewById(R.id.fragment_main_step);


        mLevel = (AutoResizeTextView) root.findViewById(R.id.fragment_main_level);
        mExpProgress = (ProgressBar) root.findViewById(R.id.fragment_main_exp_progress);
        mStepsLeft = (TextView) root.findViewById(R.id.fragment_main_steps_left);
        mStepsStatus = (TextView) root.findViewById(R.id.fragment_main_steps_status);
        mGold = (TextView) root.findViewById(R.id.fragment_main_gold);

        applyAvatarImage();
        applyData();

        return root;
    }

    private void applyAvatarImage() {
        mAvatar.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.knight));
    }

    private void applyData() {
        int exp = 3500;
        int level = calculateLevel(exp);
        int target = (level + 1) * 1320;

        mBpm.setText(NumberFormat.getIntegerInstance().format(105));
        mStep.setText(NumberFormat.getIntegerInstance().format(exp));

        mLevel.setText(NumberFormat.getInstance().format(calculateLevel(exp)));
        mExpProgress.setProgress((int) (((float) exp / (float) target) * 100));
        mStepsLeft.setText(String.format("%s steps to next level",
                NumberFormat.getIntegerInstance().format(target - exp)));

        mStepsStatus.setText(String.format("%d/%d", exp, target));
        mGold.setText(NumberFormat.getIntegerInstance().format(1000));
    }

    private int calculateLevel(int exp) {
        return exp / 1320;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCallback.updateToolbarTitle("Main");
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
