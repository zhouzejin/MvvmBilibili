package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

/**
 * The type Result adapter.
 * Created by Zhou Zejin on 2017/9/26.
 */
public class ResultAdapter extends BaseAdapter<BangumiRecommendResult> {

    private final BangumiViewModel mViewModel;

    public ResultAdapter(BangumiViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(inflater, R.layout.item_result, parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewModel viewModel = mViewModel.new ResultBodyViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
