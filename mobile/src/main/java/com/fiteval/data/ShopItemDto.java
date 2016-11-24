package com.fiteval.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry Moon on 11/17/2016.
 */

public class ShopItemDto implements Parcelable {
    public enum ItemType {head, body, weapon, foot}

    public ItemType type;
    public String id;
    public String itemName;
    public int cost;
    public int itemImage;
    public int wearImage;

    public boolean isPurchased;
    public boolean isWearing;
    public boolean isDataLoaded;

    public ShopItemDto(String id, ItemType type, String itemName, int cost, int itemImage, int wearImage) {
        this.id = id;
        this.type = type;
        this.itemName = itemName;
        this.cost = cost;
        this.itemImage = itemImage;
        this.wearImage = wearImage;
    }

    public ShopItemDto(String id, String itemName, int cost, int itemImage, int wearImage, boolean isPurchased, boolean isEquipped) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
        this.itemImage = itemImage;
        this.isPurchased = isPurchased;
        this.isWearing = isEquipped;
    }

    protected ShopItemDto(Parcel in) {
        id = in.readString();
        itemName = in.readString();
        cost = in.readInt();
        itemImage = in.readInt();
        wearImage = in.readInt();
        isPurchased = in.readByte() != 0;
        isWearing = in.readByte() != 0;
        isDataLoaded = in.readByte() != 0;
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
        dest.writeInt(itemImage);
        dest.writeInt(wearImage);
        dest.writeByte((byte) (isPurchased ? 1 : 0));
        dest.writeByte((byte) (isWearing ? 1 : 0));
        dest.writeByte((byte) (isDataLoaded ? 1 : 0));
    }
}