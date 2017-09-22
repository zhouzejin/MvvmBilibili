package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.LiveCover;
import com.sunny.mvvmbilibili.data.model.pojo.LiveOwner;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveInfo implements Parcelable {

    public abstract LiveOwner owner();
    public abstract LiveCover cover();
    public abstract String title();
    public abstract int room_id();
    public abstract int check_version();
    public abstract int online();
    public abstract String area();
    public abstract int area_id();
    @Nullable
    public abstract String playurl();
    public abstract String accept_quality();
    public abstract int broadcast_type();
    public abstract int is_tv();

    public static TypeAdapter<LiveInfo> typeAdapter(Gson gson) {
        return new AutoValue_LiveInfo.GsonTypeAdapter(gson);
    }

}
