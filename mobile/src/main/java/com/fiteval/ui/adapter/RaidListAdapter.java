package com.fiteval.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.ui.dialog.CommonAlertDialog;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.ui.item.LeaderboardListItem;
import com.fiteval.ui.item.RaidListItem;

import java.util.List;

/**
 * Created by Henry Moon on 12/2/2016.
 */
public class RaidListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RaidListItem> list;
    private LayoutInflater inflater;
    private SimpleAlertDialog mDialog;

    public RaidListAdapter(Context context, List<RaidListItem> list) {
        this.mContext = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDialog = new SimpleAlertDialog(mContext);
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
        final RaidViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_raid_list, parent, false);

            viewHolder = new RaidViewHolder();
            viewHolder.rewardLayout = (LinearLayout) convertView.findViewById(R.id.item_raid_list_reward);
            viewHolder.claimedLayout = (TextView) convertView.findViewById(R.id.item_raid_list_already_claimed);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.item_raid_list_pic);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_raid_list_title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.item_raid_list_description);
            viewHolder.gold = (TextView) convertView.findViewById(R.id.item_raid_list_gold);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.item_raid_list_duration);
            viewHolder.claimButton = (Button) convertView.findViewById(R.id.item_raid_list_claim_btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RaidViewHolder) convertView.getTag();
        }

        final RaidListItem item = list.get(position);
        viewHolder.picture.setImageDrawable(ContextCompat.getDrawable(mContext, item.picture));
        viewHolder.title.setText(item.title);
        viewHolder.description.setText(item.description);
        viewHolder.gold.setText(String.valueOf(item.gold));
        viewHolder.duration.setText(String.format("Available from %s to %s", item.startDate, item.endDate));
        viewHolder.claimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show("Success! You got 50 golds for the visit!");
                item.isClaimed = true;
                notifyDataSetChanged();
            }
        });

        if (item.isClaimed) {
            viewHolder.claimButton.setVisibility(View.GONE);
            viewHolder.rewardLayout.setVisibility(View.GONE);
            viewHolder.claimedLayout.setVisibility(View.VISIBLE);
            viewHolder.description.setVisibility(View.GONE);
            viewHolder.duration.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class RaidViewHolder {
        private LinearLayout rewardLayout;
        private TextView claimedLayout;
        private ImageView picture;
        private TextView title;
        private TextView description;
        private TextView gold;
        private TextView duration;
        private Button claimButton;
    }
}
