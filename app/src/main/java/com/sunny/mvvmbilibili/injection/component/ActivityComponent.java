package com.sunny.mvvmbilibili.injection.component;

import dagger.Subcomponent;
import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.scope.InActivity;

/**
 * This is a Dagger component. Refer to {@link BiliBiliApplication} for the list of Dagger components
 * used in this application.
 */
@InActivity
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

}
