package com.sunny.mvvmbilibili;

import android.app.Application;
import android.content.Context;

import com.sunny.mvvmbilibili.injection.component.ApplicationComponent;
import com.sunny.mvvmbilibili.injection.component.DaggerApplicationComponent;
import com.sunny.mvvmbilibili.injection.module.ApplicationModule;
import com.sunny.mvvmbilibili.utils.LeakCanaryUtil;
import com.sunny.mvvmbilibili.utils.LogUtil;

public class BiliBiliApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.initLog();
        LeakCanaryUtil.initLeakCanary(this);
    }

    public static BiliBiliApplication get(Context context) {
        return (BiliBiliApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
