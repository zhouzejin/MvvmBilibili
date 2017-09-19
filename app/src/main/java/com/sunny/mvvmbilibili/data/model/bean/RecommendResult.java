package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendBody;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendHead;

import java.util.List;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

@AutoValue
public abstract class RecommendResult implements Parcelable {

    public abstract String type();
    public abstract RecommendHead head();
    public abstract List<RecommendBody> body();

    public static TypeAdapter<RecommendResult> typeAdapter(Gson gson) {
        return new AutoValue_RecommendResult.GsonTypeAdapter(gson);
    }

}
