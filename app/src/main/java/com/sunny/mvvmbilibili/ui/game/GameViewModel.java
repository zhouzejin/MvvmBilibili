package com.sunny.mvvmbilibili.ui.game;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;
import com.sunny.mvvmbilibili.data.model.bean.VipGameInfo;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Game view model.
 */

@ConfigPersistent
public class GameViewModel extends BaseViewModel<GameMvvmView> {

    // These observable fields will update Views automatically
    public final ObservableField<VipGameInfo> vipgameinfo = new ObservableField<>();
    public final ObservableList<GameInfo> items = new ObservableArrayList<>();

    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public GameViewModel(DataManager dataManager, ImageLoader imageLoader) {
        sImageLoader = imageLoader;
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
        getMvvmView().backView();
    }

    public void loadData() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getVipGameInfo()
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                        showProgress();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<VipGameInfo>() {
                    @Override
                    public void accept(VipGameInfo vipGameInfo) throws Exception {
                        vipgameinfo.set(vipGameInfo);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<VipGameInfo, ObservableSource<List<GameInfo>>>() {
                    @Override
                    public ObservableSource<List<GameInfo>> apply(@NonNull VipGameInfo vipGameInfo) throws Exception {
                        return mDataManager.getGameInfo();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GameInfo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
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

    public void onClickHeader(VipGameInfo vipGameInfo) {
        getMvvmView().goVipGiftView(vipGameInfo);
    }

    /*****
     * Inner ViewModel
     *****/

    public class GameInfoViewModel extends BaseViewModel {

        public final ObservableField<GameInfo> gameinfo = new ObservableField<>();

        public GameInfoViewModel(GameInfo gameInfo) {
            gameinfo.set(gameInfo);
        }

        public void onClickItem(GameInfo gameInfo) {
            GameViewModel.this.getMvvmView().goGameDetailView(gameInfo);
        }
    }

}
