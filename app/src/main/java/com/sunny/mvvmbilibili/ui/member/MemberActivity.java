package com.sunny.mvvmbilibili.ui.member;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.Html;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityMemberBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;

/**
 * The type Member activity.
 * Created by Zhou Zejin on 2017/8/31.
 */
public class MemberActivity extends BaseActivity {

    private static final String VIP_URL = "http://vip.bilibili.com/site/vip-faq-h5.html#yv1";

    private ActivityMemberBinding mBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MemberActivity.class);
        return intent;
    }

    @Override
    public void initComponent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_member);
        mBinding.setActivity(this);
        setContentView(mBinding.getRoot());

        // noinspection deprecation
        mBinding.tvNoVip.setText(Html.fromHtml(getString(R.string.no_vip)));
        mBinding.webView.loadUrl(VIP_URL);
    }

    public void onClickNavigation() {
        onBackPressed();
    }

}
