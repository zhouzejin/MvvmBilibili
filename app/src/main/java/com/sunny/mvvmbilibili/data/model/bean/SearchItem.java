package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.pojo.SearchArchive;
import com.sunny.mvvmbilibili.data.model.pojo.SearchMovie;
import com.sunny.mvvmbilibili.data.model.pojo.SearchSeason;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchItem implements Parcelable {

    @Nullable
    public abstract List<SearchSeason> season();
    @Nullable
    public abstract List<SearchMovie> movie();
    @Nullable
    public abstract List<SearchArchive> archive();

    public static TypeAdapter<SearchItem> typeAdapter(Gson gson) {
        return new AutoValue_SearchItem.GsonTypeAdapter(gson);
    }

}
