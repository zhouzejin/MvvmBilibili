package com.sunny.mvvmbilibili.data.model.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sunny.mvvmbilibili.data.model.bean.Subject;

import java.util.List;

/**
 * Created by Zhou Zejin on 2016/9/12.
 */

@AutoValue
public abstract class InTheatersEntity implements Parcelable {

    public abstract int count();
    public abstract int start();
    public abstract int total();
    public abstract String title();
    public abstract List<Subject> subjects();

    public static InTheatersEntity create(int count, int start, int total,
                                          String title, List<Subject> subjects) {
        return new AutoValue_InTheatersEntity(count, start, total, title, subjects);
    }

    public static TypeAdapter<InTheatersEntity> typeAdapter(Gson gson) {
        return new AutoValue_InTheatersEntity.GsonTypeAdapter(gson);
    }

}
