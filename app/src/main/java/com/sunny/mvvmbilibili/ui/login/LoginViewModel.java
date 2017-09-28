package com.sunny.mvvmbilibili.ui.login;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;
import com.sunny.mvvmbilibili.utils.NetworkUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;

import javax.inject.Inject;

/**
 * The type Login view model.
 * Created by Zhou Zejin on 2017/8/4.
 */

@ConfigPersistent
public class LoginViewModel extends BaseViewModel<LoginMvvmView> {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getNavigationIcon() {
            return R.drawable.ic_close;
        }

        @Override
        public int getToolbarTitle() {
            return R.string.login;
        }

        @Override
        public void onClickNavigation() {
            getMvvmView().closeView();
        }
    };

    // These observable fields will update Views automatically
    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<Boolean> isShowClearIcon = new ObservableField<>();
    public final ObservableField<Boolean> isShowLoginIcon = new ObservableField<>();

    private final Context mContext;
    private final DataManager mDataManager;

    @Inject
    public LoginViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void onUsernameFocusChange(boolean hasFocus) {
        if (hasFocus && !TextUtils.isEmpty(username.get())) {
            isShowClearIcon.set(true);
        } else {
            isShowClearIcon.set(false);
        }

        if (hasFocus) isShowLoginIcon.set(true);
    }

    public void onPasswordFocusChange(boolean hasFocus) {
        if (hasFocus) isShowLoginIcon.set(false);
    }

    public TextWatcher getUsernameWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password.set("");
                if (TextUtils.isEmpty(s)) {
                    isShowClearIcon.set(false);
                } else {
                    isShowClearIcon.set(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void onClickClear() {
        username.set("");
        password.set("");
    }

    public void onClickLogin() {
        if (!NetworkUtil.isNetworkConnected(mContext)) {
            ToastUtil.showShort(mContext, mContext.getString(R.string.net_not_available));
            return;
        }

        if (TextUtils.isEmpty(username.get())) {
            ToastUtil.showShort(mContext, mContext.getString(R.string.username_empty));
            return;
        }

        if (TextUtils.isEmpty(password.get())) {
            ToastUtil.showShort(mContext, mContext.getString(R.string.password_empty));
            return;
        }

        login();
    }

    private void login() {
        mDataManager.setLogin(true);
        getMvvmView().goHomeView();
        getMvvmView().closeView();
    }

}
