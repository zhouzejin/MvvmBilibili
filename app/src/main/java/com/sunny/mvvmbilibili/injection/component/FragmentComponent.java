package com.sunny.mvvmbilibili.injection.component;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.module.FragmentModule;
import com.sunny.mvvmbilibili.injection.scope.InFragment;
import com.sunny.mvvmbilibili.ui.example.MainFragment;
import com.sunny.mvvmbilibili.ui.home.HomeFragment;

import dagger.Subcomponent;

/**
 * This is a Dagger component. Refer to {@link BiliBiliApplication} for the list of Dagger components
 * used in this application.
 */
@InFragment
@Subcomponent(modules = {ActivityModule.class, FragmentModule.class})
public interface FragmentComponent {

    void inject(MainFragment mainFragment);
    void inject(HomeFragment homeFragment);

}
