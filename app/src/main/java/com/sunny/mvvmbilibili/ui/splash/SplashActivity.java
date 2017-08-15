package com.sunny.mvvmbilibili.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.injection.component.ConfigPersistentComponent;
import com.sunny.mvvmbilibili.injection.component.DaggerConfigPersistentComponent;
import com.sunny.mvvmbilibili.injection.module.ActivityModule;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.example.MainActivity;
import com.sunny.mvvmbilibili.ui.login.LoginActivity;
import com.sunny.mvvmbilibili.utils.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * App启动页面
 * 该页面不要继承AppCompatActivity, 因为加载主题会导致界面启动卡顿。
 *
 * Created by Zhou zejin on 2017/8/3.
 */
public class SplashActivity extends Activity {

    private static final long DELAY = 2000L;

    private Disposable mDisposable;

    @Inject @ActivityContext Context mContext;
    @Inject DataManager mDataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject instance for activity
        ConfigPersistentComponent component = DaggerConfigPersistentComponent.builder()
                .applicationComponent(BiliBiliApplication.get(this).getComponent()).build();
        component.activityComponent(new ActivityModule(this)).inject(this);

        // 全屏、半透明、透明主题时，让Layout延伸到StatusBar和NavigationBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_splash);

        setUpSplash();
    }

    @Override
    protected void onDestroy() {
        RxUtil.dispose(mDisposable);

        super.onDestroy();
    }

    private void setUpSplash() {
        mDisposable = Observable.timer(DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        finishTask();
                    }
                });
    }

    private void finishTask() {
        if (mDataManager.isLogin()) {
            startActivity(new Intent(mContext, MainActivity.class));
        } else {
            startActivity(LoginActivity.getStartIntent(mContext));
        }
        finish();
    }

}
