package com.sunny.mvvmbilibili.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import com.sunny.mvvmbilibili.injection.qualifier.FragmentContext;

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    @FragmentContext
    Context providesContext() {
        return mFragment.getContext();
    }
}
