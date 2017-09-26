package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiResult implements Parcelable {

    public abstract BangumiAd ad();
    public abstract BangumiPrevious previous();
    public abstract List<BangumiSerializing> serializing();

    public static TypeAdapter<BangumiResult> typeAdapter(Gson gson) {
        return new AutoValue_BangumiResult.GsonTypeAdapter(gson);
    }

}
