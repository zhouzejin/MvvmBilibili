package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiList;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiPrevious implements Parcelable {

    public abstract int season();
    public abstract int year();
    public abstract List<BangumiList> list();

    public static TypeAdapter<BangumiPrevious> typeAdapter(Gson gson) {
        return new AutoValue_BangumiPrevious.GsonTypeAdapter(gson);
    }

}
