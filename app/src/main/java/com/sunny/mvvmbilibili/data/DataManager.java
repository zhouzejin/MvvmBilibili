package com.sunny.mvvmbilibili.data;

import com.sunny.mvvmbilibili.data.local.DatabaseHelper;
import com.sunny.mvvmbilibili.data.local.PreferencesHelper;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.data.model.entity.InTheatersEntity;
import com.sunny.mvvmbilibili.data.remote.RetrofitService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Singleton
public class DataManager {

    private final PreferencesHelper mPreferencesHelper;
    private final DatabaseHelper mDatabaseHelper;
    private final RetrofitService mRetrofitService;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper, DatabaseHelper databaseHelper,
                       RetrofitService retrofitService) {
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mRetrofitService = retrofitService;
    }

    /*****
     * Preferences Data Source
     *****/

    public void setLogin(Boolean isLogin) {
        mPreferencesHelper.putBoolean(PreferencesHelper.KEY_IS_LOGIN, isLogin);
    }

    public Boolean isLogin() {
        return mPreferencesHelper.getBoolean(PreferencesHelper.KEY_IS_LOGIN, false);
    }

    /*****
     * Database Data Source
     *****/

    public Observable<List<Subject>> getSubjects() {
        return mDatabaseHelper.getSubjects().distinct();
    }

    /*****
     * Net Data Source
     *****/

    public Observable<Subject> syncSubjects() {
        return mRetrofitService.getSubjects()
                .concatMap(new Function<InTheatersEntity, ObservableSource<? extends Subject>>() {
                    @Override
                    public ObservableSource<? extends Subject> apply(@NonNull InTheatersEntity inTheatersEntity) throws Exception {
                        return mDatabaseHelper.setSubjects(inTheatersEntity.subjects());
                    }
                });
    }

}
