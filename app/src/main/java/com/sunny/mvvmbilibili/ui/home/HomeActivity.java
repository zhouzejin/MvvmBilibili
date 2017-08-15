package com.sunny.mvvmbilibili.ui.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityHomeBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;

/**
 * The type Home activity.
 * Created by Zhou Zejin on 2017/8/14.
 */
public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding mBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initComponent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setContentView(mBinding.getRoot());
    }

}
