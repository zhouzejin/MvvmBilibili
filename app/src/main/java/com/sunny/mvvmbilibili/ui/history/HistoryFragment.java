package com.sunny.mvvmbilibili.ui.history;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.FragmentHistoryBinding;
import com.sunny.mvvmbilibili.ui.base.BaseFragment;
import com.sunny.mvvmbilibili.ui.home.HomeActivity;

import javax.inject.Inject;

/**
 * The type History fragment.
 * Created by Zhou Zejin on 2017/8/29.
 */
public class HistoryFragment extends BaseFragment implements HistoryMvvmView {

    @Inject HistoryViewModel mViewModel;
    @Inject Activity mActivity;

    private FragmentHistoryBinding mBinding;

    public HistoryFragment() {
        // Requires empty public constructor
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject instance for fragment
        fragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
        mViewModel.isShowContentEmpty.set(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mViewModel.detachView();
    }

    /*****
     * MVVM View methods implementation
     *****/

    @Override
    public void toggleDrawerLayout() {
        if (mActivity instanceof HomeActivity) {
            ((HomeActivity) mActivity).toggleDrawer();
        }
    }

}
