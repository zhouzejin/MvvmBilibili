package com.sunny.mvvmbilibili.ui.setting;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Setting mvvm view.
 * Created by Zhou Zejin on 2017/8/29.
 */
public interface SettingMvvmView extends MvvmView {

    void toggleDrawerLayout();

    void closeAndGoLoginView();

    void goAboutMeView();

    void goAboutAppView();

}
