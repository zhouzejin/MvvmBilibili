package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Zhou Zejin on 2016/9/14.
 */

@AutoValue
public abstract class Rating implements Parcelable {

    public abstract int max();
    public abstract double average();
    public abstract String stars();
    public abstract int min();

    public static Rating create(int max, double average, String stars, int min) {
        return new AutoValue_Rating(max, average, stars, min);
    }

    public static TypeAdapter<Rating> typeAdapter(Gson gson) {
        return new AutoValue_Rating.GsonTypeAdapter(gson);
    }

}
