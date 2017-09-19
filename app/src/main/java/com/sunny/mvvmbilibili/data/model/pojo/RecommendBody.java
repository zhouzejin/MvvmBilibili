package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

@AutoValue
public abstract class RecommendBody implements Parcelable {

    public abstract String title();
    public abstract String style();
    public abstract String cover();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoStr();
    public abstract int width();
    public abstract int height();
    @Nullable
    public abstract String play();
    @Nullable
    public abstract String danmaku();
    @Nullable
    public abstract String up();
    @Nullable
    public abstract String up_face();
    @Nullable
    public abstract Long online();
    @Nullable
    public abstract String area();
    @Nullable
    public abstract Integer area_id();
    @Nullable
    public abstract String desc1();
    @Nullable
    public abstract Integer status();

    public static TypeAdapter<RecommendBody> typeAdapter(Gson gson) {
        return new AutoValue_RecommendBody.GsonTypeAdapter(gson);
    }

}
