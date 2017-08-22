package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;

import java.util.List;

/**
 * Created by Zhou Zejin on 2017/8/21.
 */

@AutoValue
public abstract class GameInfoEntity implements Parcelable {

    public abstract long timestamp();
    public abstract int code();
    public abstract List<GameInfo> items();

    public static TypeAdapter<GameInfoEntity> typeAdapter(Gson gson) {
        return new AutoValue_GameInfoEntity.GsonTypeAdapter(gson);
    }

}
