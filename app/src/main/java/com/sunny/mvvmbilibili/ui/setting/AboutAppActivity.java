package com.sunny.mvvmbilibili.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityAboutAppBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;
import com.sunny.mvvmbilibili.utils.SystemUtil;
import com.sunny.mvvmbilibili.utils.factory.DialogFactory;

/**
 * The type About app activity.
 * Created by Zhou Zejin on 2017/8/30.
 */
public class AboutAppActivity extends BaseActivity {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getToolbarTitle() {
            return R.string.about_app;
        }

        @Override
        public void onClickNavigation() {
            onBackPressed();
        }
    };

    public final ObservableField<String> version = new ObservableField<>();

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AboutAppActivity.class);
        return intent;
    }

    @Override
    public void initComponent() {
        ActivityAboutAppBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_app);
        binding.setActivity(this);
        setContentView(binding.getRoot());

        version.set(getString(R.string.version, SystemUtil.getVersionName(this)));
    }

    public void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_url_hint) + getString(R.string.github_url));
        startActivity(Intent.createChooser(intent, getString(R.string.share_url_title)));
    }

    public void feedback() {
        DialogFactory.createSimpleOkErrorDialog(this,
                R.string.feedback,
                R.string.feedback_message).show();
    }

}
