package com.sunny.mvvmbilibili.ui.layout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.sunny.mvvmbilibili.R;

/**
 * The type Content empty layout.
 * Created by Zhou Zejin on 2017/9/28.
 */
public class ContentEmptyLayout extends BaseObservable {

    public final ObservableField<Boolean> isShowContentEmpty = new ObservableField<>();

    @Bindable
    public @DrawableRes int getContentEmptyImg() {
        return R.drawable.img_default_image;
    }

    @Bindable
    public @StringRes int getContentEmptyHint() {
        return R.string.content_empty;
    }

}
