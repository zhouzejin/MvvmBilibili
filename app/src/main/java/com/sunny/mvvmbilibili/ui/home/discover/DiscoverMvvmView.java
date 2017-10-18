package com.sunny.mvvmbilibili.ui.home.discover;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Discover mvvm view.
 * Created by Zhou Zejin on 2017/10/17.
 */
public interface DiscoverMvvmView extends MvvmView {

    void showHotWord();

    void goSearchView(String queryStr);

}
