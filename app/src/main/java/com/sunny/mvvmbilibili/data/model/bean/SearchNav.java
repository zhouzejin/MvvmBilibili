package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchNav implements Parcelable {

    public abstract String name();
    public abstract int total();
    public abstract int pages();
    public abstract int type();

    public static TypeAdapter<SearchNav> typeAdapter(Gson gson) {
        return new AutoValue_SearchNav.GsonTypeAdapter(gson);
    }

}
