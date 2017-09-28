package com.sunny.mvvmbilibili.ui.setting;

import android.content.Context;
import android.databinding.Bindable;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;
import com.sunny.mvvmbilibili.utils.SystemUtil;

import javax.inject.Inject;

/**
 * The type Setting view model.
 * Created by Zhou Zejin on 2017/8/29.
 */

@ConfigPersistent
public class SettingViewModel extends BaseViewModel<SettingMvvmView> {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getNavigationIcon() {
            return R.drawable.ic_drawer;
        }

        @Override
        public int getToolbarTitle() {
            return R.string.action_setting;
        }

        @Override
        public void onClickNavigation() {
            getMvvmView().toggleDrawerLayout();
        }
    };

    private final Context mContext;
    private final DataManager mDataManager;

    @Inject
    public SettingViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SettingMvvmView mvvmView) {
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

    @Bindable
    public String getVersionName() {
        return mContext.getString(R.string.version, SystemUtil.getVersionName(mContext));
    }

    public void loginOut() {
        mDataManager.setLogin(false);
        getMvvmView().closeAndGoLoginView();
    }

    public void goAboutMe() {
        getMvvmView().goAboutMeView();
    }

    public void goAboutApp() {
        getMvvmView().goAboutAppView();
    }

}
