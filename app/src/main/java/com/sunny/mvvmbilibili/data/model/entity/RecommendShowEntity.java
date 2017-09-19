package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.RecommendResult;

import java.util.List;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

@AutoValue
public abstract class RecommendShowEntity implements Parcelable {

    public abstract int code();
    public abstract List<RecommendResult> result();

    public static TypeAdapter<RecommendShowEntity> typeAdapter(Gson gson) {
        return new AutoValue_RecommendShowEntity.GsonTypeAdapter(gson);
    }

}
