package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfos;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveInfoEntity implements Parcelable {

    public abstract int code();
    public abstract String message();
    public abstract LiveInfos data();

    public static TypeAdapter<LiveInfoEntity> typeAdapter(Gson gson) {
        return new AutoValue_LiveInfoEntity.GsonTypeAdapter(gson);
    }

}
