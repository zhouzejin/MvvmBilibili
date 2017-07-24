package com.sunny.mvvmbilibili.injection.component;

import dagger.Component;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.module.FragmentModule;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example ViewModels).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);
    FragmentComponent fragmentComponent(ActivityModule activityModule, FragmentModule fragmentModule);

}
