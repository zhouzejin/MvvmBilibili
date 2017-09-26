package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.BangumiResult;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiInfoEntity implements Parcelable {

    public abstract int code();
    public abstract String message();
    public abstract BangumiResult result();

    public static TypeAdapter<BangumiInfoEntity> typeAdapter(Gson gson) {
        return new AutoValue_BangumiInfoEntity.GsonTypeAdapter(gson);
    }

}
