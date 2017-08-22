package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Zhou Zejine on 2017/8/22.
 */

@AutoValue
public abstract class VipGameInfo implements Parcelable {

    public abstract String link();
    public abstract String imgPath();

    public static TypeAdapter<VipGameInfo> typeAdapter(Gson gson) {
        return new AutoValue_VipGameInfo.GsonTypeAdapter(gson);
    }

}
