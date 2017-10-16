package com.sunny.mvvmbilibili.ui.search.upper;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Search upper mvvm view.
 * Created by Zhou Zejin on 2017/10/16.
 */
public interface SearchUpperMvvmView extends MvvmView {

    void showSearching();

    void hideSearching();

    void setRecyclerScrollLoading(boolean isLoading);

    void setCurrentPage(int pageNum);

}
