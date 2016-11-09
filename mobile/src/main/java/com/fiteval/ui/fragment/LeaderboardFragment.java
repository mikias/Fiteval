package com.fiteval.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.ui.adapter.LeaderboardListAdapter;
import com.fiteval.ui.item.LeaderboardListItem;
import com.fiteval.util.MiscUtil;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private CheckBox mCheckBox;
    private ListView mListView;
    private ProgressBar mLoadingProgress;
    private LeaderboardListAdapter mAdapter;
    private MiscUtil mUtils;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        mContext = getActivity();
        mCheckBox = (CheckBox) root.findViewById(R.id.fragment_leaderboard_checkbox);
        mListView = (ListView) root.findViewById(R.id.fragment_leaderboard_listview);
        mLoadingProgress = (ProgressBar) root.findViewById(R.id.fragment_leaderboard_progress);

        mUtils = new MiscUtil(mContext);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUtils.toastCenter("To Be Implemented");
            }
        });

        List<LeaderboardListItem> list = new ArrayList<>();
        list.add(new LeaderboardListItem("1", "GoldKnight", "85"));
        list.add(new LeaderboardListItem("2", "SilverKnight", "76"));
        list.add(new LeaderboardListItem("3", "BronzeKnight", "53"));
        list.add(new LeaderboardListItem("4", "Henry", "45"));
        list.add(new LeaderboardListItem("5", "Mikias", "42"));
        list.add(new LeaderboardListItem("6", "Nader", "37"));
        list.add(new LeaderboardListItem("7", "Travis", "35"));
        list.add(new LeaderboardListItem("8", "McCrickard", "24"));
        list.add(new LeaderboardListItem("10", "Reinhardt", "20"));
        list.add(new LeaderboardListItem("11", "Lucio", "17"));
        list.add(new LeaderboardListItem("12", "Symmetra", "9"));
        populateList(list);

        return root;
    }

    private void populateList(List<LeaderboardListItem> list) {
        mAdapter = new LeaderboardListAdapter(mContext, list);

        // simulate network loading
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingWait();
                mListView.setAdapter(mAdapter);
            }
        }, 1000);
    }

    private void dismissLoadingWait() {
        mLoadingProgress.setVisibility(View.GONE);
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
