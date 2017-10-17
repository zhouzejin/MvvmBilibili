package com.sunny.mvvmbilibili.ui.home.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.FragmentDiscoverBinding;
import com.sunny.mvvmbilibili.ui.base.LazyLoadFragment;

import javax.inject.Inject;

/**
 * The type Discover fragment.
 * Created by Administrator on 2017/10/17.
 */
public class DiscoverFragment extends LazyLoadFragment implements DiscoverMvvmView {

    @Inject DiscoverViewModel mViewModel;

    private FragmentDiscoverBinding mBinding;

    public DiscoverFragment() {

    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        mViewModel.detachView();
        super.onDestroyView();
    }

}
