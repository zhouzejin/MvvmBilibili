package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveCover implements Parcelable {

    public abstract String src();
    public abstract int height();
    public abstract int width();

    public static TypeAdapter<LiveCover> typeAdapter(Gson gson) {
        return new AutoValue_LiveCover.GsonTypeAdapter(gson);
    }

}
