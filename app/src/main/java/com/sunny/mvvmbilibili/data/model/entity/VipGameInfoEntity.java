package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.VipGameInfo;

/**
 * Created by Zhou Zejin on 2017/8/22.
 */

@AutoValue
public abstract class VipGameInfoEntity implements Parcelable {

    public abstract int code();
    public abstract VipGameInfo data();
    public abstract String msg();
    public abstract long ts();

    public static TypeAdapter<VipGameInfoEntity> typeAdapter(Gson gson) {
        return new AutoValue_VipGameInfoEntity.GsonTypeAdapter(gson);
    }

}
