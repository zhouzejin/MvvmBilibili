package com.sunny.mvvmbilibili.ui.example;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import com.sunny.mvvmbilibili.BR;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.databinding.ItemSubjectBinding;
import com.sunny.mvvmbilibili.ui.base.BaseAdapter;
import com.sunny.mvvmbilibili.ui.base.ViewModel;

public class SubjectsAdapter extends BaseAdapter<Subject> {

    @Inject
    public SubjectsAdapter() {

    }

    @Override
    public ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent) {
        return ItemSubjectBinding.inflate(inflater, parent, false);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, int position) {
        ViewModel viewModel = new MainViewModel.SubjectViewModel(mData.get(position));
        holder.getBinding().setVariable(BR.viewmodel, viewModel);
        super.onBindViewHolder(holder, position);
    }

}
