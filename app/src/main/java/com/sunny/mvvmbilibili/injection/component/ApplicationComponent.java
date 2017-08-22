package com.sunny.mvvmbilibili.injection.component;

import android.app.Application;
import android.content.Context;

import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.SyncService;
import com.sunny.mvvmbilibili.data.local.DatabaseHelper;
import com.sunny.mvvmbilibili.data.local.FileHelper;
import com.sunny.mvvmbilibili.data.local.PreferencesHelper;
import com.sunny.mvvmbilibili.data.remote.RetrofitHelper;
import com.sunny.mvvmbilibili.injection.module.ApplicationModule;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;
import com.sunny.mvvmbilibili.utils.singleton.RxBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    PreferencesHelper preferencesHelper();
    FileHelper fileHelper();
    DatabaseHelper databaseHelper();
    RetrofitHelper retrofitHelper();
    DataManager dataManager();
    RxBus eventBus();
    ImageLoader imageLoader();

}
