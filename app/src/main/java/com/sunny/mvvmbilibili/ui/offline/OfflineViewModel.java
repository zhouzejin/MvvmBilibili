package com.sunny.mvvmbilibili.ui.offline;

import android.content.Context;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.base.MvvmView;

import javax.inject.Inject;

/**
 * The type Offline view model.
 * Created by Zhou Zejin on 2017/8/25.
 */

@ConfigPersistent
public class OfflineViewModel extends BaseViewModel<MvvmView> {

    private final Context mContext;

    @Inject
    public OfflineViewModel(@ApplicationContext Context context) {
        mContext = context;
        isShowContentEmpty.set(true);
    }

    @Override
    public void attachView(MvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public int getContentEmptyImg() {
        return R.drawable.img_no_cache;
    }

    @Override
    public int getContentEmptyHint() {
        return R.string.no_cache;
    }

}
