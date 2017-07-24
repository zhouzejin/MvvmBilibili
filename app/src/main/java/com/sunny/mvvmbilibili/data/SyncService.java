package com.sunny.mvvmbilibili.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.sunny.mvvmbilibili.BiliBiliApplication;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.utils.AndroidComponentUtil;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.NetworkUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class SyncService extends Service {

    @Inject DataManager mDataManager;
    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtil.isServiceRunning(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BiliBiliApplication.get(this).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        LogUtil.i("Starting sync...");

        if (!NetworkUtil.isNetworkConnected(this)) {
            LogUtil.i("Sync canceled, connection not available");
            AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.syncSubjects()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Subject>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("Synced successfully!");
                        stopSelf(startId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.w(e, "Error syncing.");
                        stopSelf(startId);

                    }

                    @Override
                    public void onNext(Subject subject) {
                    }
                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) mSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtil.isNetworkConnected(context)) {
                LogUtil.i("Connection is now available, triggering sync...");
                AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }

}
