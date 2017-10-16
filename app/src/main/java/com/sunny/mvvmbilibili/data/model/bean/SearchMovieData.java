package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.SearchMovie;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/16.
 */

@AutoValue
public abstract class SearchMovieData implements Parcelable {

    public abstract String trackid();
    public abstract int pages();
    @Nullable
    public abstract List<SearchMovie> items();

    public static TypeAdapter<SearchMovieData> typeAdapter(Gson gson) {
        return new AutoValue_SearchMovieData.GsonTypeAdapter(gson);
    }

}
