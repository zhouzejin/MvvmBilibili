package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

@AutoValue
public abstract class BangumiRecommendEntity implements Parcelable {

    public abstract int code();
    public abstract String message();
    public abstract List<BangumiRecommendResult> result();

    public static TypeAdapter<BangumiRecommendEntity> typeAdapter(Gson gson) {
        return new AutoValue_BangumiRecommendEntity.GsonTypeAdapter(gson);
    }

}
