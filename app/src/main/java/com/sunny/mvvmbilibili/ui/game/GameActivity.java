package com.sunny.mvvmbilibili.ui.game;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityGameBinding;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * The type Game activity.
 * Created by Zhou Zejin on 2017/8/18.
 */
public class GameActivity extends BaseActivity implements GameMvvmView {

    @Inject GameViewModel mViewModel;
    @Inject GameInfoAdapter mAdapter;
    @Inject @ActivityContext Context mContext;

    private ActivityGameBinding mBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.attachView(this);
        mViewModel.loadGameInfo();
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
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

    @Override
    public void closeView() {
        finish();
    }

}
