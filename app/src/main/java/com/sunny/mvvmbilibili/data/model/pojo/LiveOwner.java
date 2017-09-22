package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveOwner implements Parcelable {

    public abstract String face();
    public abstract int mid();
    public abstract String name();

    public static TypeAdapter<LiveOwner> typeAdapter(Gson gson) {
        return new AutoValue_LiveOwner.GsonTypeAdapter(gson);
    }

}
