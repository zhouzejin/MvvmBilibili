package com.sunny.mvvmbilibili.ui.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BindingViewHolder> {

    protected List<T> mData = new ArrayList<>();

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = getBinding(inflater, parent);
        return new BindingViewHolder<>(binding);
    }

    public abstract ViewDataBinding getBinding(LayoutInflater inflater, ViewGroup parent);

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, int position) {
        holder.getBinding().executePendingBindings(); // 强制绑定后立即刷新，避免数据闪烁
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        protected final T mBinding;

        public BindingViewHolder(T binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }

}
