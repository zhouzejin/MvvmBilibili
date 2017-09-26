package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiBody implements Parcelable {

    public abstract String img();
    public abstract int index();
    public abstract String link();
    public abstract String title();
    public abstract int wid();

    public static TypeAdapter<BangumiBody> typeAdapter(Gson gson) {
        return new AutoValue_BangumiBody.GsonTypeAdapter(gson);
    }

}
