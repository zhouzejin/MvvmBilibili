package com.sunny.mvvmbilibili.ui.example;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.databinding.ItemSubjectBinding;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

import javax.inject.Inject;

public class SubjectsAdapter extends BaseAdapter<Subject> {

    private final MainViewModel mMainViewModel;

    @Inject
    public SubjectsAdapter(MainViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ItemSubjectBinding.inflate(inflater, parent, false);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, int position) {
        ViewModel viewModel = mMainViewModel.new SubjectViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
