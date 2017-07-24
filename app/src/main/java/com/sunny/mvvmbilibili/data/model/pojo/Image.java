package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Zhou Zejin on 2016/9/14.
 */

@AutoValue
public abstract class Image implements Parcelable {

    public abstract String small();
    public abstract String large();
    public abstract String medium();

    public static Image create(String small, String large, String medium) {
        return new AutoValue_Image(small, large, medium);
    }

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new AutoValue_Image.GsonTypeAdapter(gson);
    }

}
