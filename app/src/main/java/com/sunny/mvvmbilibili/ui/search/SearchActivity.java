package com.sunny.mvvmbilibili.ui.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivitySearchBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * The type Search activity.
 * Created by Zhou Zejin on 2017/9/27.
 */
public class SearchActivity extends BaseActivity implements SearchMvvmView {

    private static final String EXTRA_QUERY_STRING =
            "com.sunny.mvvmbilibili.ui.search.SearchActivity.EXTRA_QUERY_STRING";

    @Inject SearchViewModel mViewModel;

    private ActivitySearchBinding mBinding;

    public static Intent getStartIntent(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_QUERY_STRING, query);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.attachView(this);
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onDestroy() {
        mViewModel.detachView();
        super.onDestroy();
    }

    /*****
     * MVVM View methods implementation
     *****/

}
