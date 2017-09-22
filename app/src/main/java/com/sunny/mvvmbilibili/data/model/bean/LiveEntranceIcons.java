package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.LiveEntranceIcon;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveEntranceIcons implements Parcelable {

    public abstract int id();
    public abstract String name();
    public abstract LiveEntranceIcon entrance_icon();

    public static TypeAdapter<LiveEntranceIcons> typeAdapter(Gson gson) {
        return new AutoValue_LiveEntranceIcons.GsonTypeAdapter(gson);
    }

}
