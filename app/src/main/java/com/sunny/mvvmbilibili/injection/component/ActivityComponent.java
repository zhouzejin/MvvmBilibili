package com.sunny.mvvmbilibili.injection.component;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.scope.InActivity;
import com.sunny.mvvmbilibili.ui.login.LoginActivity;
import com.sunny.mvvmbilibili.ui.splash.SplashActivity;

import dagger.Subcomponent;

/**
 * This is a Dagger component. Refer to {@link BiliBiliApplication} for the list of Dagger components
 * used in this application.
 */
@InActivity
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);

}
