package com.sunny.mvvmbilibili.data;

import com.sunny.mvvmbilibili.data.local.DatabaseHelper;
import com.sunny.mvvmbilibili.data.local.PreferencesHelper;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.data.model.entity.InTheatersEntity;
import com.sunny.mvvmbilibili.data.remote.RetrofitService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final RetrofitService mRetrofitService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RetrofitService retrofitService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRetrofitService = retrofitService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Subject> syncSubjects() {
        return mRetrofitService.getSubjects()
                .concatMap(new Func1<InTheatersEntity, Observable<? extends Subject>>() {
                    @Override
                    public Observable<? extends Subject> call(InTheatersEntity inTheatersEntity) {
                        return mDatabaseHelper.setSubjects(inTheatersEntity.subjects());
                    }
                });
    }

    public Observable<List<Subject>> getSubjects() {
        return mDatabaseHelper.getSubjects().distinct();
    }

}
