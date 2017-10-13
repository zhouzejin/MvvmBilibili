package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.SearchSeason;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/13.
 */

@AutoValue
public abstract class SearchBangumiData implements Parcelable {

    public abstract String trackid();
    public abstract int pages();
    @Nullable
    public abstract List<SearchSeason> items();

    public static TypeAdapter<SearchBangumiData> typeAdapter(Gson gson) {
        return new AutoValue_SearchBangumiData.GsonTypeAdapter(gson);
    }

}
