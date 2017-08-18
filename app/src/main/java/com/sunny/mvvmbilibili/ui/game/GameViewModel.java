package com.sunny.mvvmbilibili.ui.game;

import android.content.Context;

import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * The type Game view model.
 */

@ConfigPersistent
public class GameViewModel extends BaseViewModel<GameMvvmView> {

    private final Context mContext;

    @Inject
    public GameViewModel(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public void attachView(GameMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
