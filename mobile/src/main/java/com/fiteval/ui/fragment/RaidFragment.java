package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.model.Raid;
import com.fiteval.model.RaidList;

import java.util.ArrayList;

public class RaidFragment extends Fragment {

    RecyclerView mRecycler;
    public static final String TAG = MainFragment.class.getName();

    public RaidFragment() {
        // Required empty public constructor
    }

    public static RaidFragment newInstance() {
        RaidFragment fragment = new RaidFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_raid, container, false);

        mRecycler = (RecyclerView) root.findViewById(R.id.raid_recyclerView);

        mRecycler.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Adapter adapter = new Adapter();
        mRecycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRecycler.setAdapter(null);
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.RaidViewHolder>{

        ArrayList<Raid> list;

        Adapter(){
            list = RaidList.createList();
        }

        @Override
        public RaidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.raid_card, parent, false);
            return new RaidViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RaidViewHolder holder, int position) {
            holder.locName.setText(list.get(position).getName());
            holder.locPhoto.setImageResource(list.get(position).getPhoto());
            holder.locRaid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO check to see if user is close enough to location, then raid
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        class RaidViewHolder extends RecyclerView.ViewHolder {
            TextView locName;
            Button locRaid;
            ImageView locPhoto;

            RaidViewHolder(View itemView) {
                super(itemView);

                locName = (TextView) itemView.findViewById(R.id.tv_location);
                locRaid = (Button)itemView.findViewById(R.id.button_raid);
                locPhoto = (ImageView)itemView.findViewById(R.id.iv_location);
            }
        }

    }
}
