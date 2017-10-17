package com.sunny.mvvmbilibili.ui.home.discover;

import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * The type Discover view model.
 * Created by Zhou Zejin on 2017/10/17.
 */

@ConfigPersistent
public class DiscoverViewModel extends BaseViewModel<DiscoverMvvmView> {

    @Inject
    public DiscoverViewModel() {
    }

    @Override
    public void attachView(DiscoverMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
