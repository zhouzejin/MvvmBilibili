package com.sunny.mvvmbilibili.ui.base;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.injection.component.ActivityComponent;
import com.sunny.mvvmbilibili.injection.component.ConfigPersistentComponent;
import com.sunny.mvvmbilibili.injection.component.DaggerConfigPersistentComponent;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.utils.LogUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private static final LongSparseArray<ConfigPersistentComponent> sComponentsMap =
            new LongSparseArray<>();

    private ConfigPersistentComponent mConfigPersistentComponent;
    private ActivityComponent mActivityComponent;
    private long mActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createComponent(savedInstanceState);
        mActivityComponent = mConfigPersistentComponent.activityComponent(new ActivityModule(this));

        initComponent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            LogUtil.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    /**
     * Create the ConfigPersistentComponent and reuses cached ConfigPersistentComponent if this is
     * being called after a configuration change.
     */
    private void createComponent(Bundle savedInstanceState) {
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        mConfigPersistentComponent = sComponentsMap.get(mActivityId, null);
        if (mConfigPersistentComponent == null) {
            LogUtil.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            mConfigPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BiliBiliApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, mConfigPersistentComponent);
        }
    }

    /**
     * 初始化组件
     */
    public abstract void initComponent();

    public ConfigPersistentComponent configPersistentComponent() {
        return mConfigPersistentComponent;
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

}
