package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import io.reactivex.annotations.Nullable;

/**
 * Created by Zhou Zejin on 2016/9/14.
 */

@AutoValue
public abstract class Person implements Parcelable {

    @Nullable
    public abstract String alt();
    @Nullable
    public abstract Image avatars();
    public abstract String name();
    @Nullable
    public abstract String id();

    public static Person create(String alt, Image avatars, String name, String id) {
        return new AutoValue_Person(alt, avatars, name, id);
    }

    public static TypeAdapter<Person> typeAdapter(Gson gson) {
        return new AutoValue_Person.GsonTypeAdapter(gson);
    }

}
