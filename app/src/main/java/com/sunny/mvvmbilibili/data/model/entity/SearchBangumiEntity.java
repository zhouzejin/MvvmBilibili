package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.SearchBangumiData;

/**
 * Created by Administrator on 2017/10/13.
 */

@AutoValue
public abstract class SearchBangumiEntity implements Parcelable {

    public abstract int code();
    public abstract SearchBangumiData data();
    public abstract String message();
    public abstract int ttl();

    public static TypeAdapter<SearchBangumiEntity> typeAdapter(Gson gson) {
        return new AutoValue_SearchBangumiEntity.GsonTypeAdapter(gson);
    }

}
