package com.sunny.mvvmbilibili.ui.attention;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ContentEmptyLayout;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;

import javax.inject.Inject;

/**
 * The type Attention view model.
 * Created by Zhou Zejin on 2017/8/29.
 */

@ConfigPersistent
public class AttentionViewModel extends BaseViewModel<AttentionMvvmView> {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getNavigationIcon() {
            return R.drawable.ic_drawer;
        }

        @Override
        public int getToolbarTitle() {
            return R.string.action_attention;
        }

        @Override
        public void onClickNavigation() {
            getMvvmView().toggleDrawerLayout();
        }
    };
    public final ContentEmptyLayout contentEmptyLayout = new ContentEmptyLayout() {
        @Override
        public int getContentEmptyImg() {
            return R.drawable.img_no_attention;
        }

        @Override
        public int getContentEmptyHint() {
            return R.string.no_attention;
        }
    };

    @Inject
    public AttentionViewModel() {
    }

    @Override
    public void attachView(AttentionMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
