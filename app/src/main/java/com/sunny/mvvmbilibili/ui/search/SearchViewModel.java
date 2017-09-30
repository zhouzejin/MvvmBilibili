package com.sunny.mvvmbilibili.ui.search;

import android.content.Context;

import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.SearchLayout;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

/**
 * The type Search view model.
 * Created by Zhou Zejin on 2017/9/27.
 */
@ConfigPersistent
public class SearchViewModel extends BaseViewModel<SearchMvvmView> {

    public final SearchLayout searchLayout = new SearchLayout() {
        @Override
        public void back() {
            getMvvmView().backView();
        }

        @Override
        public void search() {
            query(searchStr.get());
        }
    };

    public final Context mContext;

    @Inject
    public SearchViewModel(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public void attachView(SearchMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void query(String content) {
        ToastUtil.showShort(mContext, content);
    }

}
