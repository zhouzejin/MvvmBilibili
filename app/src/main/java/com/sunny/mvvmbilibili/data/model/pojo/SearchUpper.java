package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/16.
 */

@AutoValue
public abstract class SearchUpper implements Parcelable {

    public abstract String title();
    @Nullable
    public abstract String name();
    public abstract String cover();
    public abstract String uri();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoX();
    @Nullable
    public abstract String sign();
    public abstract int fans();
    public abstract int level();
    public abstract int archives();
    public abstract int mid();
    public abstract int live_status();

    public static TypeAdapter<SearchUpper> typeAdapter(Gson gson) {
        return new AutoValue_SearchUpper.GsonTypeAdapter(gson);
    }

}
