package com.sunny.mvvmbilibili.ui.home.live;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.LivePartitions;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Live section.
 * Created by Zhou Zejin on 2017/9/22.
 */
public class PartitionSection extends StatelessSection {

    private static final int COUNT = 4;

    private LiveViewModel mViewModel;
    private LivePartitions mPartitions;

    public PartitionSection(LiveViewModel viewModel, LivePartitions partitions) {
        super(new SectionParameters.Builder(R.layout.layout_partition_item)
                .headerResourceId(R.layout.layout_partition_header)
                .build());
        mViewModel = viewModel;
        mPartitions = partitions;
    }

    @Override
    public int getContentItemsTotal() {
        // 限制显示数量不超过一定数目
        return mPartitions.lives().size() > COUNT ? COUNT : mPartitions.lives().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        ViewModel viewModel = mViewModel.new PartitionBodyViewModel(mPartitions.lives().get(position));
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
        ViewModel viewModel = mViewModel.new PartitionHeadViewModel(mPartitions.partition());
        bindingViewHolder.getBinding().setVariable(BR.viewmodel, viewModel);
        bindingViewHolder.getBinding().executePendingBindings();
    }

}
