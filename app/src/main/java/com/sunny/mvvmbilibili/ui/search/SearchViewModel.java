package com.sunny.mvvmbilibili.ui.search;

import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * The type Search view model.
 * Created by Zhou Zejin on 2017/9/27.
 */
@ConfigPersistent
public class SearchViewModel extends BaseViewModel<SearchMvvmView> {

    @Inject
    public SearchViewModel() {
    }

    @Override
    public void attachView(SearchMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
