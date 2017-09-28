package com.sunny.mvvmbilibili.ui.layout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.sunny.mvvmbilibili.R;

/**
 * The type Toolbar layout.
 * Created by Zhou Zejin on 2017/9/28.
 */
public class ToolbarLayout extends BaseObservable {

    @Bindable
    public @DrawableRes int getNavigationIcon() {
        return R.drawable.ic_back;
    }

    @Bindable
    public @StringRes int getToolbarTitle() {
        return R.string.app_name;
    }

    public void onClickNavigation() {
    }

}
