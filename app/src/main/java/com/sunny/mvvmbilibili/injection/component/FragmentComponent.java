package com.sunny.mvvmbilibili.injection.component;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.module.FragmentModule;
import com.sunny.mvvmbilibili.injection.scope.InFragment;
import com.sunny.mvvmbilibili.ui.attention.AttentionFragment;
import com.sunny.mvvmbilibili.ui.example.MainFragment;
import com.sunny.mvvmbilibili.ui.favourite.FavouriteFragment;
import com.sunny.mvvmbilibili.ui.history.HistoryFragment;
import com.sunny.mvvmbilibili.ui.home.HomeFragment;
import com.sunny.mvvmbilibili.ui.setting.SettingFragment;
import com.sunny.mvvmbilibili.ui.wallet.WalletFragment;

import dagger.Subcomponent;

/**
 * This is a Dagger component. Refer to {@link BiliBiliApplication} for the list of Dagger components
 * used in this application.
 */
@InFragment
@Subcomponent(modules = {ActivityModule.class, FragmentModule.class})
public interface FragmentComponent {

    void inject(MainFragment fragment);
    void inject(HomeFragment fragment);
    void inject(FavouriteFragment fragment);
    void inject(HistoryFragment fragment);
    void inject(AttentionFragment fragment);
    void inject(WalletFragment fragment);
    void inject(SettingFragment fragment);

}
