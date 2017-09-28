package com.sunny.mvvmbilibili.ui.home.recommend;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendBody;
import com.sunny.mvvmbilibili.databinding.ItemActivityBinding;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

/**
 * The type Activity adapter.
 * Created by Zhou Zejin on 2017/9/19.
 */

public class ActivityAdapter extends BaseAdapter<RecommendBody> {

    private final RecommendViewModel mViewModel;

    public ActivityAdapter(RecommendViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ItemActivityBinding.inflate(inflater, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = mViewModel.new ResultBodyViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
