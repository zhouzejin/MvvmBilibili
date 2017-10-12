package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.SearchData;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchArchiveEntity implements Parcelable {

    public abstract int code();
    public abstract SearchData data();
    public abstract String message();
    public abstract int ttl();

    public static TypeAdapter<SearchArchiveEntity> typeAdapter(Gson gson) {
        return new AutoValue_SearchArchiveEntity.GsonTypeAdapter(gson);
    }

}
