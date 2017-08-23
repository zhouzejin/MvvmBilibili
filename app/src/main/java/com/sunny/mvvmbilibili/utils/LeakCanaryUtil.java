package com.sunny.mvvmbilibili.utils;

import android.app.Application;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Zhou Zejin on 2017/7/31.
 * The type Leak canary util.
 */
public class LeakCanaryUtil {

    public static void initLeakCanary(Application context) {
        if (LeakCanary.isInAnalyzerProcess(context)) {
            // This process is dedicated to LeakCanaryUtil for heap analysis.
            // You should not init your app in this process.
            return;
        }

        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
                .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
                .build();

        LeakCanary.refWatcher(context)
                .listenerServiceClass(DisplayLeakService.class)
                .excludedRefs(excludedRefs)
                .buildAndInstall();

//        LeakCanary.install(context);
    }

}
