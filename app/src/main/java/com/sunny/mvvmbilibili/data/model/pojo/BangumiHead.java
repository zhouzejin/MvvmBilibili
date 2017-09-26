package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiHead implements Parcelable {

    public abstract int id();
    public abstract String img();
    public abstract String link();
    public abstract String pub_time();
    public abstract String title();

    public static TypeAdapter<BangumiHead> typeAdapter(Gson gson) {
        return new AutoValue_BangumiHead.GsonTypeAdapter(gson);
    }

}
