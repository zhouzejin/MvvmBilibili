package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/9/26.
 */

@AutoValue
public abstract class BangumiRecommendResult implements Parcelable {

    public abstract String cover();
    public abstract long cursor();
    @Nullable
    public abstract String desc();
    public abstract int id();
    public abstract int is_new();
    public abstract String link();
    public abstract String title();
    public abstract int type();
    public abstract int wid();

    public static TypeAdapter<BangumiRecommendResult> typeAdapter(Gson gson) {
        return new AutoValue_BangumiRecommendResult.GsonTypeAdapter(gson);
    }

}
