package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.HotWord;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

@AutoValue
public abstract class HotWordEntity implements Parcelable {

    public abstract int code();
    public abstract String seid();
    public abstract int timestamp();
    public abstract String message();
    public abstract List<HotWord> list();

    public static TypeAdapter<HotWordEntity> typeAdapter(Gson gson) {
        return new AutoValue_HotWordEntity.GsonTypeAdapter(gson);
    }

}
