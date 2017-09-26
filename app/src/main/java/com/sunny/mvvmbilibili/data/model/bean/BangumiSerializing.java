package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiSerializing implements Parcelable {

    public abstract String cover();
    public abstract String favourites();
    public abstract int is_finish();
    public abstract int is_started();
    public abstract int last_time();
    public abstract String newest_ep_index();
    public abstract int pub_time();
    public abstract int season_id();
    public abstract int season_status();
    public abstract String title();
    public abstract int watching_count();

    public static TypeAdapter<BangumiSerializing> typeAdapter(Gson gson) {
        return new AutoValue_BangumiSerializing.GsonTypeAdapter(gson);
    }

}
