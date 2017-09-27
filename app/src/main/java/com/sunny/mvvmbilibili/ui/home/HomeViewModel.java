package com.sunny.mvvmbilibili.ui.home;

import android.content.Context;
import android.databinding.Bindable;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;

import javax.inject.Inject;

/**
 * The type Home view model.
 * Created by Zhou Zejin on 2017/8/16.
 */

@ConfigPersistent
public class HomeViewModel extends BaseViewModel<HomeMvvmView> {

    private final Context mContext;

    @Inject
    public HomeViewModel(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public void attachView(HomeMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Bindable
    public String[] getSuggestions() {
        return mContext.getResources().getStringArray(R.array.search_suggestions);
    }

    public MaterialSearchView.OnQueryTextListener getQueryTextListener() {
        return new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getMvvmView().showSearchView(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    public void onUserInfoLayoutClick() {
        getMvvmView().toggleDrawerLayout();
    }

}
