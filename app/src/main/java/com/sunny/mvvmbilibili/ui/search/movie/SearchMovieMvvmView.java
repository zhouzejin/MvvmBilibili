package com.sunny.mvvmbilibili.ui.search.movie;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Search movie mvvm view.
 *  Created by Zhou Zejin on 2017/10/16.
 */
public interface SearchMovieMvvmView extends MvvmView {

    void showSearching();

    void hideSearching();

    void setRecyclerScrollLoading(boolean isLoading);

    void setCurrentPage(int pageNum);

}
