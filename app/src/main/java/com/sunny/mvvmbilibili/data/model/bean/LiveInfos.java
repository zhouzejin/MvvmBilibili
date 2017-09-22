package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

@AutoValue
public abstract class LiveInfos implements Parcelable {

    public abstract List<LiveBanner> banner();
    public abstract List<LiveEntranceIcons> entranceIcons();
    public abstract List<LivePartitions> partitions();

    public static TypeAdapter<LiveInfos> typeAdapter(Gson gson) {
        return new AutoValue_LiveInfos.GsonTypeAdapter(gson);
    }

}
