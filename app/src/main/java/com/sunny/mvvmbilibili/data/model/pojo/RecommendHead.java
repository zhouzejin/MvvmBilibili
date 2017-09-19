package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/11.
 */

@AutoValue
public abstract class RecommendHead implements Parcelable {

    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoStr();
    public abstract String style();
    public abstract String title();

    public static TypeAdapter<RecommendHead> typeAdapter(Gson gson) {
        return new AutoValue_RecommendHead.GsonTypeAdapter(gson);
    }

}
