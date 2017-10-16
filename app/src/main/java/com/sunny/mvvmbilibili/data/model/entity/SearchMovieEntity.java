package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.SearchMovieData;

/**
 * Created by Administrator on 2017/10/16.
 */

@AutoValue
public abstract class SearchMovieEntity implements Parcelable {

    public abstract int code();
    public abstract SearchMovieData data();
    public abstract String message();
    public abstract int ttl();

    public static TypeAdapter<SearchMovieEntity> typeAdapter(Gson gson) {
        return new AutoValue_SearchMovieEntity.GsonTypeAdapter(gson);
    }

}
