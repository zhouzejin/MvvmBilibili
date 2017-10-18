package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/10/17.
 */

@AutoValue
public abstract class HotWord implements Parcelable {

    public abstract String status();
    public abstract String keyword();

    public static TypeAdapter<HotWord> typeAdapter(Gson gson) {
        return new AutoValue_HotWord.GsonTypeAdapter(gson);
    }

}
