package com.sunny.mvvmbilibili.ui.setting;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.FragmentSettingBinding;
import com.sunny.mvvmbilibili.ui.base.BaseFragment;
import com.sunny.mvvmbilibili.ui.home.HomeActivity;
import com.sunny.mvvmbilibili.ui.login.LoginActivity;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

/**
 * The type Setting fragment.
 * Created by Zhou Zejin on 2017/8/29.
 */
public class SettingFragment extends BaseFragment implements SettingMvvmView {

    @Inject
    SettingViewModel mViewModel;
    @Inject
    Activity mActivity;

    private FragmentSettingBinding mBinding;

    public SettingFragment() {
        // Requires empty public constructor
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.attachView(this);
        mBinding.setViewmodel(mViewModel);
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

    @Override
    public void closeAndGoLoginView() {
        startActivity(LoginActivity.getStartIntent(mActivity));
        mActivity.finish();
    }

    @Override
    public void goAboutMeView() {
        ToastUtil.showShort(mActivity, "About me");
    }

    @Override
    public void goAboutAppView() {
        ToastUtil.showShort(mActivity, "About app");
    }

}
