package com.sunny.mvvmbilibili.ui.wallet;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ContentEmptyLayout;

import javax.inject.Inject;

/**
 * The type Wallet view model.
 * Created by Zhou Zejin on 2017/8/29.
 */

@ConfigPersistent
public class WalletViewModel extends BaseViewModel<WalletMvvmView> {

    public final ContentEmptyLayout contentEmptyLayout = new ContentEmptyLayout() {
        @Override
        public int getContentEmptyImg() {
            return R.drawable.img_no_wallet;
        }

        @Override
        public int getContentEmptyHint() {
            return R.string.no_wallet;
        }
    };

    @Inject
    public WalletViewModel() {
    }

    @Override
    public void attachView(WalletMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public int getNavigationIcon() {
        return R.drawable.ic_drawer;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.action_wallet;
    }

    @Override
    public void onClickNavigation() {
        getMvvmView().toggleDrawerLayout();
    }

}
