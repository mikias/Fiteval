package com.fiteval.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry Moon on 11/17/2016.
 */

public class ShopItemDto implements Parcelable{
    public String id;
    public int cost;
    public boolean isEquipped;
    public String itemName;
    public boolean isPurchased;

    public ShopItemDto(String itemName, int cost) {
        this.itemName = itemName;
        this.cost = cost;
    }

    protected ShopItemDto(Parcel in) {
        id = in.readString();
        cost = in.readInt();
        isEquipped = in.readByte() != 0;
        itemName = in.readString();
        isPurchased = in.readByte() != 0;
    }

    public static final Creator<ShopItemDto> CREATOR = new Creator<ShopItemDto>() {
        @Override
        public ShopItemDto createFromParcel(Parcel in) {
            return new ShopItemDto(in);
        }

        @Override
        public ShopItemDto[] newArray(int size) {
            return new ShopItemDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(cost);
        dest.writeByte((byte) (isEquipped ? 1 : 0));
        dest.writeString(itemName);
        dest.writeByte((byte) (isPurchased ? 1 : 0));
    }
}