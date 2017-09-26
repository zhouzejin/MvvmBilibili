package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiBody;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiHead;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

@AutoValue
public abstract class BangumiAd implements Parcelable {

    public abstract List<BangumiBody> body();
    public abstract List<BangumiHead> head();

    public static TypeAdapter<BangumiAd> typeAdapter(Gson gson) {
        return new AutoValue_BangumiAd.GsonTypeAdapter(gson);
    }

}
