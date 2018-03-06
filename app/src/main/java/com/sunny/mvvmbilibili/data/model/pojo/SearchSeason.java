package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/10/10.
 */

@AutoValue
public abstract class SearchSeason implements Parcelable {

    public abstract String title();
    @Nullable
    public abstract String name();
    public abstract String cover();
    public abstract String uri();
    public abstract String param();
    @SerializedName("goto")
    public abstract String gotoX();
    public abstract int finish();
    public abstract String index();
    public abstract String newest_cat();
    public abstract String newest_season();
    @Nullable
    public abstract String cat_desc();
    public abstract int total_count();

    public static TypeAdapter<SearchSeason> typeAdapter(Gson gson) {
        return new AutoValue_SearchSeason.GsonTypeAdapter(gson);
    }

}
