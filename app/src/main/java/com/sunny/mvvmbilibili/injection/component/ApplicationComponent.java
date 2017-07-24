package com.sunny.mvvmbilibili.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.SyncService;
import com.sunny.mvvmbilibili.data.local.DatabaseHelper;
import com.sunny.mvvmbilibili.data.local.PreferencesHelper;
import com.sunny.mvvmbilibili.data.remote.RetrofitService;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.module.ApplicationModule;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;
import com.sunny.mvvmbilibili.utils.singleton.RxBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RetrofitService retrofitService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxBus eventBus();
    ImageLoader imageLoader();

}
