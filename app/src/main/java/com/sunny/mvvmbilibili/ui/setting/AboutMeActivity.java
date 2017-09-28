package com.sunny.mvvmbilibili.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityAboutMeBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;

/**
 * The type About me activity.
 * Created by Zhou Zejin on 2017/8/30.
 */
public class AboutMeActivity extends BaseActivity {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getToolbarTitle() {
            return R.string.about_me;
        }

        @Override
        public void onClickNavigation() {
            onBackPressed();
        }
    };

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        return intent;
    }

    @Override
    public void initComponent() {
        ActivityAboutMeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me);
        binding.setActivity(this);
        setContentView(binding.getRoot());
    }

}
