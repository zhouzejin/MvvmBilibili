package com.sunny.mvvmbilibili.ui.favourite;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ContentEmptyLayout;
import com.sunny.mvvmbilibili.ui.layout.ToolbarLayout;

import javax.inject.Inject;

/**
 * The type Favourite view model.
 * Created by Zhou Zejin on 2017/8/29.
 */

@ConfigPersistent
public class FavouriteViewModel extends BaseViewModel<FavouriteMvvmView> {

    public final ToolbarLayout toolbarLayout = new ToolbarLayout() {
        @Override
        public int getNavigationIcon() {
            return R.drawable.ic_drawer;
        }

        @Override
        public int getToolbarTitle() {
            return R.string.action_favourite;
        }

        @Override
        public void onClickNavigation() {
            getMvvmView().toggleDrawerLayout();
        }
    };
    public final ContentEmptyLayout contentEmptyLayout = new ContentEmptyLayout() {
        @Override
        public int getContentEmptyImg() {
            return R.drawable.img_no_favourite;
        }

        @Override
        public int getContentEmptyHint() {
            return R.string.no_favourite;
        }
    };

    @Inject
    public FavouriteViewModel() {
    }

    @Override
    public void attachView(FavouriteMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
