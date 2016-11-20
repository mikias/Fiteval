package com.fiteval.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.ui.item.LeaderboardListItem;

import java.util.List;

/**
 * Created by Henry Moon on 11/7/2016.
 */

public class LeaderboardListAdapter extends BaseAdapter {

    private List<LeaderboardListItem> list;
    private LayoutInflater inflater;

    public LeaderboardListAdapter(Context context, List<LeaderboardListItem> list) {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeaderViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_leaderboard_list, parent, false);
            viewHolder = new LeaderViewHolder();
            viewHolder.rank = (TextView) convertView.findViewById(R.id.item_leaderboard_list_rank);
            viewHolder.nickName = (TextView) convertView.findViewById(R.id.item_leaderboard_list_nickname);
            viewHolder.level = (TextView) convertView.findViewById(R.id.item_leaderboard_list_level);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (LeaderViewHolder) convertView.getTag();
        }

        LeaderboardListItem item = list.get(position);
        viewHolder.rank.setText(item.rank());
        viewHolder.nickName.setText(item.nickName());
        viewHolder.level.setText(item.level());

        return convertView;
    }

    private class LeaderViewHolder {
        private TextView rank;
        private TextView nickName;
        private TextView level;
    }
}
