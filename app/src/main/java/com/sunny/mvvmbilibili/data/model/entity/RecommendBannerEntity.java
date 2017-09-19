package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.RecommendBanner;

import java.util.List;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

@AutoValue
public abstract class RecommendBannerEntity implements Parcelable {

    public abstract int code();
    public abstract List<RecommendBanner> data();

    public static TypeAdapter<RecommendBannerEntity> typeAdapter(Gson gson) {
        return new AutoValue_RecommendBannerEntity.GsonTypeAdapter(gson);
    }

}
