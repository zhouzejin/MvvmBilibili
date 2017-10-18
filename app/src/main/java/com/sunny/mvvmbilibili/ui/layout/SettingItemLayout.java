package com.sunny.mvvmbilibili.ui.layout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.sunny.mvvmbilibili.R;

/**
 * The type Setting item layout.
 * Created by Administrator on 2017/10/18.
 */
public class SettingItemLayout extends BaseObservable {

    @Bindable
    public @DrawableRes int getItemIcon() {
        return R.drawable.img_default_image;
    }

    @Bindable
    public @StringRes int getItemText() {
        return R.string.app_name;
    }

    public boolean isShowArrow() {
        return true;
    }

}
