package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Zhou Zejin on 2017/8/21.
 */

@AutoValue
public abstract class GameInfo implements Parcelable {

    public abstract String title();
    public abstract String summary();
    public abstract String download_link();
    public abstract String cover();

    public static TypeAdapter<GameInfo> typeAdapter(Gson gson) {
        return new AutoValue_GameInfo.GsonTypeAdapter(gson);
    }

}
