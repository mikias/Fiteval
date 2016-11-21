package com.fiteval.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry Moon on 11/17/2016.
 */

public class ShopItemDto implements Parcelable{
    public String id;
    public String itemName;
    public int cost;
    public Drawable image;

    public boolean isPurchased;
    public boolean isWearing;

    public ShopItemDto(String id, String itemName, int cost, Drawable image) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
        this.image = image;
    }

    public ShopItemDto(String id, String itemName, int cost, Drawable image,
                       boolean isPurchased, boolean isEquipped) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
        this.image = image;
        this.isPurchased = isPurchased;
        this.isWearing = isEquipped;
    }

    protected ShopItemDto(Parcel in) {
        id = in.readString();
        itemName = in.readString();
        cost = in.readInt();
        isPurchased = in.readByte() != 0;
        isWearing = in.readByte() != 0;
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
        dest.writeString(itemName);
        dest.writeInt(cost);
        dest.writeByte((byte) (isPurchased ? 1 : 0));
        dest.writeByte((byte) (isWearing ? 1 : 0));
    }
}