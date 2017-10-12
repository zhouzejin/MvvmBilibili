package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchData implements Parcelable {

    public abstract String trackid();
    public abstract int page();
    public abstract SearchItem items();
    public abstract List<SearchNav> nav();

    public static TypeAdapter<SearchData> typeAdapter(Gson gson) {
        return new AutoValue_SearchData.GsonTypeAdapter(gson);
    }

}
