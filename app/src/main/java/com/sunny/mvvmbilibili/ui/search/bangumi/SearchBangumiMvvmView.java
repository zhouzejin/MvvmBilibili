package com.sunny.mvvmbilibili.ui.search.bangumi;

import com.sunny.mvvmbilibili.ui.base.MvvmView;

/**
 * The interface Search bangumi mvvm view.
 * Created by Zhou Zejin on 2017/10/13.
 */
public interface SearchBangumiMvvmView extends MvvmView {

    void showSearching();

    void hideSearching();

    void setRecyclerScrollLoading(boolean isLoading);

    void setCurrentPage(int pageNum);

}
