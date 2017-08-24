package com.sunny.mvvmbilibili.ui.game;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;
import com.sunny.mvvmbilibili.data.model.bean.VipGameInfo;
import com.sunny.mvvmbilibili.databinding.ActivityGameBinding;
import com.sunny.mvvmbilibili.injection.qualifier.ActivityContext;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.base.HeaderAndFooterWrappedAdapter;
import com.sunny.mvvmbilibili.ui.browser.BrowserActivity;

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
        mViewModel.loadData();
    }

    @Override
    public void initComponent() {
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initAdapter();
        mBinding.setViewmodel(mViewModel);
        setContentView(mBinding.getRoot());
    }

    private void initAdapter() {
        HeaderAndFooterWrappedAdapter wrappedAdapter = new HeaderAndFooterWrappedAdapter(mAdapter);
        ViewDataBinding header = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.layout_game_header, mBinding.recyclerView, false);
        header.setVariable(BR.viewmodel, mViewModel); // 切记这里要设置ViewModel
        wrappedAdapter.addHeaderView(header);
        mBinding.recyclerView.setAdapter(wrappedAdapter);
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
    public void backView() {
        onBackPressed();
    }

    @Override
    public void goVipGiftView(VipGameInfo vipGameInfo) {
        startActivity(BrowserActivity.getStartIntent(mContext,
                vipGameInfo.link(), getString(R.string.game_vip_zone)));
    }

    @Override
    public void goGameDetailView(GameInfo gameInfo) {
        startActivity(BrowserActivity.getStartIntent(mContext,
                gameInfo.download_link(), gameInfo.title()));
    }

}
