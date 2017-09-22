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
public abstract class LivePartitions implements Parcelable {

    public abstract LivePartition partition();
    public abstract List<LiveInfo> lives();

    public static TypeAdapter<LivePartitions> typeAdapter(Gson gson) {
        return new AutoValue_LivePartitions.GsonTypeAdapter(gson);
    }

}
