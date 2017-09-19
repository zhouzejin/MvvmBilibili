package com.sunny.mvvmbilibili.ui.home.recommend;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.RecommendResult;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Recommend section.
 * Created by Zhou Zejin on 2017/9/14.
 */
public class BangumiSection extends StatelessSection {

    private RecommendViewModel mViewModel;
    private RecommendResult mResult;

    public BangumiSection(RecommendViewModel viewModel, RecommendResult result) {
        super(new SectionParameters.Builder(R.layout.layout_bangumi_item)
                .headerResourceId(R.layout.layout_bangumi_header)
                .footerResourceId(R.layout.layout_bangumi_footer)
                .build());
        mViewModel = viewModel;
        mResult = result;
    }

    @Override
    public int getContentItemsTotal() {
        return mResult.body().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new ResultBodyViewModel(mResult.body().get(position));
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
        ViewModel viewModel = mViewModel.new ResultHeadViewModel(mResult.head(),
                RecommendFragment.sIconMap.get(mResult.head().title()));
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new ResultFootViewModel();
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

}
