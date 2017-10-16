package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.SearchUpper;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/16.
 */

@AutoValue
public abstract class SearchUpperData implements Parcelable {

    public abstract String trackid();
    public abstract int pages();
    @Nullable
    public abstract List<SearchUpper> items();

    public static TypeAdapter<SearchUpperData> typeAdapter(Gson gson) {
        return new AutoValue_SearchUpperData.GsonTypeAdapter(gson);
    }

}
