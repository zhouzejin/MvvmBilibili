package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveBanner implements Parcelable {

    public abstract String title();
    public abstract String img();
    public abstract String remark();
    public abstract String link();

    public static TypeAdapter<LiveBanner> typeAdapter(Gson gson) {
        return new AutoValue_LiveBanner.GsonTypeAdapter(gson);
    }

}
