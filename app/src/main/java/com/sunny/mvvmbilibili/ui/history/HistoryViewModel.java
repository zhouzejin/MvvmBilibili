package com.sunny.mvvmbilibili.ui.history;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * The type History view model.
 * Created by Zhou Zejin on 2017/8/29.
 */

@ConfigPersistent
public class HistoryViewModel extends BaseViewModel<HistoryMvvmView> {

    @Inject
    public HistoryViewModel() {
    }

    @Override
    public void attachView(HistoryMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public int getNavigationIcon() {
        return R.drawable.ic_drawer;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.action_history;
    }

    @Override
    public int getContentEmptyImg() {
        return R.drawable.img_no_history;
    }

    @Override
    public int getContentEmptyHint() {
        return R.string.no_history;
    }

    @Override
    public void onClickNavigation() {
        getMvvmView().toggleDrawerLayout();
    }

}
