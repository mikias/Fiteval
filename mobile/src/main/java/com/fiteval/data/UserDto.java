package com.fiteval.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry Moon on 11/20/2016.
 */

public class UserDto implements Parcelable {

    public String id;
    public String name;
    public int gold;
    public int exp;
    public String headItemId;
    public String bodyItemId;
    public String weaponItemId;
    public String footItemId;

    public UserDto(String id, String name, int gold, int exp,
                   String headItemId, String bodyItemId, String weaponItemId, String footItemId) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.exp = exp;
        this.headItemId = headItemId;
        this.bodyItemId = bodyItemId;
        this.weaponItemId = weaponItemId;
        this.footItemId = footItemId;
    }

    protected UserDto(Parcel in) {
        id = in.readString();
        name = in.readString();
        gold = in.readInt();
        exp = in.readInt();
        headItemId = in.readString();
        bodyItemId = in.readString();
        weaponItemId = in.readString();
        footItemId = in.readString();
    }

    public static final Creator<UserDto> CREATOR = new Creator<UserDto>() {
        @Override
        public UserDto createFromParcel(Parcel in) {
            return new UserDto(in);
        }

        @Override
        public UserDto[] newArray(int size) {
            return new UserDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(gold);
        dest.writeInt(exp);
        dest.writeString(headItemId);
        dest.writeString(bodyItemId);
        dest.writeString(weaponItemId);
        dest.writeString(footItemId);
    }
}
