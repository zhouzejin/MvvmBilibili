package com.sunny.mvvmbilibili.ui.home;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The type Home mvvm view.
 * Created by Zhou Zejin on 2017/8/16.
 */
public interface HomeMvvmView extends MvvmView {

    boolean isSearchViewOpen();

    void closeSearchView();

    void toggleDrawerLayout();

    void showSearchView(String query);

}
