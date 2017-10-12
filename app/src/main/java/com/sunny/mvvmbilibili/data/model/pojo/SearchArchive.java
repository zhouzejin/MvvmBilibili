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
public abstract class SearchArchive implements Parcelable {

    public abstract String title();
    public abstract String name();
    public abstract String cover();
    public abstract String uri();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoX();
    public abstract int play();
    public abstract int danmaku();
    public abstract String author();
    public abstract String desc();
    public abstract String duration();

    public static TypeAdapter<SearchArchive> typeAdapter(Gson gson) {
        return new AutoValue_SearchArchive.GsonTypeAdapter(gson);
    }

}
