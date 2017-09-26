package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.BangumiPrevious;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Previous section.
 * Created by Zhou Zejin on 2017/9/26.
 */
public class PreviousSection extends StatelessSection {

    private BangumiViewModel mViewModel;
    private BangumiPrevious mPrevious;

    public PreviousSection(BangumiViewModel viewModel, BangumiPrevious previous) {
        super(new SectionParameters.Builder(R.layout.layout_previous_item)
                .headerResourceId(R.layout.layout_previous_header)
                .build());

        mViewModel = viewModel;
        mPrevious = previous;
    }

    @Override
    public int getContentItemsTotal() {
        return mPrevious.list().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new PreviousBodyViewModel(mPrevious.list().get(position));
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        int season = mPrevious.season() - 1;
        if (season < 0 || season > 3) return;
        ViewModel viewModel = mViewModel.new PreviousHeaderViewModel(
                BangumiFragment.sIconArray[season], BangumiFragment.sTitleArray[season]);
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

}
