package com.sunny.mvvmbilibili.ui.game;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Game view model.
 */

@ConfigPersistent
public class GameViewModel extends BaseViewModel<GameMvvmView> {

    // These observable fields will update Views automatically
    public final ObservableList<GameInfo> items = new ObservableArrayList<>();

    private final Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public GameViewModel(@ApplicationContext Context context, DataManager dataManager,
                         ImageLoader imageLoader) {
        sImageLoader = imageLoader;
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(GameMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxUtil.dispose(mDisposable);
    }

    @Override
    public int getToolbarTitle() {
        return R.string.game_center;
    }

    @Override
    public void onClickNavigation() {
        getMvvmView().closeView();
    }

    public void loadGameInfo() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getGameInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<GameInfo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                        showProgress();
                    }

                    @Override
                    public void onNext(@NonNull List<GameInfo> gameInfos) {
                        items.clear();
                        items.addAll(gameInfos);
                        hideProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error loading the GameInfo.");
                        hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*****
     * Inner ViewModel
     *****/

    public static class GameInfoViewModel extends BaseViewModel {

        public final ObservableField<GameInfo> gameinfo = new ObservableField<>();

        public GameInfoViewModel(GameInfo gameInfo) {
            gameinfo.set(gameInfo);
        }
    }

}
