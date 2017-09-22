package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.LiveSubIcon;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LivePartition implements Parcelable {

    public abstract int id();
    public abstract String name();
    public abstract String area();
    public abstract LiveSubIcon sub_icon();
    public abstract int count();

    public static TypeAdapter<LivePartition> typeAdapter(Gson gson) {
        return new AutoValue_LivePartition.GsonTypeAdapter(gson);
    }

}
