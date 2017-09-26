package com.sunny.mvvmbilibili.data.model.pojo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by Administrator on 2017/9/26.
 */

@AutoValue
public abstract class Region implements Parcelable {

    public abstract Integer icon_res();
    public abstract String title();

    public static Region create(Integer icon_res, String title) {
        return new AutoValue_Region(icon_res, title);
    }

}
