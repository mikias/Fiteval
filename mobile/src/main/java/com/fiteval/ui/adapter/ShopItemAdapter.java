package com.fiteval.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.data.ShopItemDto;

import java.util.List;

/**
 * Created by Henry Moon on 11/17/2016.
 */

public class ShopItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ShopItemDto> mItemList;

    public ShopItemAdapter(Context mContext, List<ShopItemDto> itemList) {
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItemList = itemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopItemViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ShopItemViewHolder();
            convertView = inflater.inflate(R.layout.item_shop_list, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_shop_image);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_shop_name);
            viewHolder.cost = (TextView) convertView.findViewById(R.id.item_shop_cost);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShopItemViewHolder) convertView.getTag();
        }
        //viewHolder.image.setImageResource(mItemList.get(position));
        viewHolder.name.setText(mItemList.get(position).itemName);
        viewHolder.cost.setText(Integer.toString(mItemList.get(position).cost));

        return convertView;
    }

    private class ShopItemViewHolder {
        private ImageView image;
        private TextView name;
        private TextView cost;
    }
}
