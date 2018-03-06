package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchMovie implements Parcelable {

    public abstract String title();
    @Nullable
    public abstract String name();
    public abstract String cover();
    public abstract String uri();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoX();
    @Nullable
    public abstract String desc();
    @Nullable
    public abstract String screen_date();
    @Nullable
    public abstract String area();
    @Nullable
    public abstract String cover_mark();
    @Nullable
    public abstract String actors();
    @Nullable
    public abstract String staff();
    @Nullable
    public abstract Integer length();
    @Nullable
    public abstract Integer status();

    public static TypeAdapter<SearchMovie> typeAdapter(Gson gson) {
        return new AutoValue_SearchMovie.GsonTypeAdapter(gson);
    }

}
