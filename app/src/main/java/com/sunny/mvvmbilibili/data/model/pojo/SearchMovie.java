package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchMovie implements Parcelable {

    public abstract String title();
    public abstract String name();
    public abstract String cover();
    public abstract String uri();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoX();
    public abstract String desc();
    public abstract String screen_date();
    public abstract String area();
    public abstract String cover_mark();
    public abstract String actors();
    public abstract String staff();
    public abstract int length();
    public abstract int status();

    public static TypeAdapter<SearchMovie> typeAdapter(Gson gson) {
        return new AutoValue_SearchMovie.GsonTypeAdapter(gson);
    }

}
