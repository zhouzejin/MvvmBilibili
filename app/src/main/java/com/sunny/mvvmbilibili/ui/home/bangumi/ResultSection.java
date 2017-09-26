package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.LayoutResultFooterBinding;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * The type Result section.
 * Created by Zhou Zejin on 2017/9/26.
 */
public class ResultSection extends StatelessSection {

    private BangumiViewModel mViewModel;

    public ResultSection(BangumiViewModel viewModel) {
        super(new SectionParameters.Builder(R.layout.layout_empty)
                .headerResourceId(R.layout.layout_result_header)
                .footerResourceId(R.layout.layout_result_footer)
                .build());
        mViewModel = viewModel;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new BaseAdapter.BindingViewHolder<>(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        BaseAdapter.BindingViewHolder bindingViewHolder = (BaseAdapter.BindingViewHolder) holder;
        LayoutResultFooterBinding binding = (LayoutResultFooterBinding) bindingViewHolder.getBinding();
        binding.setVariable(BR.viewmodel, mViewModel);

        if (binding.recyclerView.getAdapter() != null) return;
        LinearLayoutManager layoutManager = new LinearLayoutManager(binding.recyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(new ResultAdapter(mViewModel));
        binding.executePendingBindings();
    }

}
