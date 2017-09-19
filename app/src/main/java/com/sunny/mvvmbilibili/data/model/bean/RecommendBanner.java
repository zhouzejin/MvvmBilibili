package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

@AutoValue
public abstract class RecommendBanner implements Parcelable {

    public abstract String title();
    public abstract String value();
    public abstract String image();
    public abstract int type();
    public abstract int weight();
    public abstract String remark();
    public abstract String hash();

    public static TypeAdapter<RecommendBanner> typeAdapter(Gson gson) {
        return new AutoValue_RecommendBanner.GsonTypeAdapter(gson);
    }

}
