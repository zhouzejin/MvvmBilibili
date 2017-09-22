package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveSubIcon implements Parcelable {

    public abstract String src();
    public abstract String height();
    public abstract String width();

    public static TypeAdapter<LiveSubIcon> typeAdapter(Gson gson) {
        return new AutoValue_LiveSubIcon.GsonTypeAdapter(gson);
    }

}
