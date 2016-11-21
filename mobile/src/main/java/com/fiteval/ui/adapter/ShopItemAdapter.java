package com.fiteval.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fiteval.R;
import com.fiteval.data.ShopItemDto;
import com.fiteval.ui.viewholder.ShopItemViewHolder;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Henry Moon on 11/17/2016.
 */

public class ShopItemAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ShopItemDto> mItemList;
    private NumberFormat mNumFormat;

    public ShopItemAdapter(Context mContext, List<ShopItemDto> itemList) {
        this.mContext = mContext;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItemList = itemList;
        this.mNumFormat = NumberFormat.getIntegerInstance();
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
            viewHolder.card = (CardView) convertView.findViewById(R.id.item_shop_card_view);
            viewHolder.itemLayout = (RelativeLayout) convertView.findViewById(R.id.item_shop_item_layout);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_shop_image);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_shop_name);
            viewHolder.priceLayout = (LinearLayout) convertView.findViewById(R.id.item_shop_price_layout);
            viewHolder.price = (TextView) convertView.findViewById(R.id.item_shop_price);
            viewHolder.wearLayout = (LinearLayout) convertView.findViewById(R.id.item_shop_wear_layout);
            viewHolder.takeOffLayout = (LinearLayout) convertView.findViewById(R.id.item_shop_take_off_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShopItemViewHolder) convertView.getTag();
        }
        //viewHolder.image.setImageResource(mItemList.get(position));
        viewHolder.name.setText(mItemList.get(position).itemName);

        ShopItemDto dto = mItemList.get(position);
        if (dto.isPurchased) {
            if (dto.isWearing) {
                // wearing - can take off
                viewHolder.priceLayout.setVisibility(View.GONE);
                viewHolder.wearLayout.setVisibility(View.GONE);
                viewHolder.takeOffLayout.setVisibility(View.VISIBLE);
                //viewHolder.itemLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_f3));
            } else {
                // not wearing - can wear
                viewHolder.priceLayout.setVisibility(View.GONE);
                viewHolder.takeOffLayout.setVisibility(View.GONE);
                viewHolder.wearLayout.setVisibility(View.VISIBLE);
                //viewHolder.itemLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            }
        } else {
            // not purchaed - can purchase
            viewHolder.price.setText(mNumFormat.format(dto.cost));
            viewHolder.wearLayout.setVisibility(View.GONE);
            viewHolder.takeOffLayout.setVisibility(View.GONE);
            viewHolder.priceLayout.setVisibility(View.VISIBLE);
            //viewHolder.itemLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }

        return convertView;
    }
}
