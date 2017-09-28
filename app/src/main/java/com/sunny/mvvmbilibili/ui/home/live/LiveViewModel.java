package com.sunny.mvvmbilibili.ui.home.live;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.ColorRes;
import android.text.Html;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.LiveBanner;
import com.sunny.mvvmbilibili.data.model.bean.LiveEntranceIcons;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfo;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfos;
import com.sunny.mvvmbilibili.data.model.bean.LivePartition;
import com.sunny.mvvmbilibili.data.model.bean.LivePartitions;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Live view model.
 * Created by Zhou Zejin on 2017/9/21.
 */

@ConfigPersistent
public class LiveViewModel extends BaseViewModel<LiveMvvmView> {

    // These observable fields will update Views automatically
    public final ObservableField<Boolean> isRefreshing = new ObservableField<>();
    public final ObservableList<LiveBanner> banners = new ObservableArrayList<>();
    public final ObservableList<LiveEntranceIcons> entranceIconses = new ObservableArrayList<>();
    public final ObservableList<LivePartitions> partitions = new ObservableArrayList<>();

    private final DataManager mDataManager;
    private final @ApplicationContext Context mContext;

    private Disposable mDisposable;

    @Inject
    public LiveViewModel(DataManager dataManager, @ApplicationContext Context context) {
        mDataManager = dataManager;
        mContext = context;
    }

    @Override
    public void attachView(LiveMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        RxUtil.dispose(mDisposable);
        super.detachView();
    }

    @Override
    public int getContentEmptyImg() {
        return R.drawable.img_load_error;
    }

    @Override
    public int getContentEmptyHint() {
        return R.string.load_error;
    }

    @Bindable public @ColorRes int[] getColorSchemeResources() {
        return new int[]{R.color.primary};
    }

    public void refresh() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getLiveInfos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                        isRefreshing.set(true);
                    }
                })
                .subscribe(new Observer<LiveInfos>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull LiveInfos liveInfos) {
                        isRefreshing.set(false);
                        isShowContentEmpty.set(false);

                        banners.clear();
                        banners.addAll(liveInfos.banner());
                        entranceIconses.clear();
                        entranceIconses.addAll(liveInfos.entranceIcons());
                        partitions.clear();
                        partitions.addAll(liveInfos.partitions());

                        getMvvmView().showLiveInfo();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error loading the LiveInfo.");
                        isRefreshing.set(false);
                        if (banners.isEmpty() && entranceIconses.isEmpty() && partitions.isEmpty())
                            isShowContentEmpty.set(true);
                        getMvvmView().showErrorHint();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*****
     * Inner ViewModel
     *****/

    public class BannerViewModel extends BaseViewModel {

        public final ObservableField<LiveBanner> banner = new ObservableField<>();

        public BannerViewModel(LiveBanner liveBanner) {
            banner.set(liveBanner);
        }

        public void onClickItem(LiveBanner banner) {
            LiveViewModel.this.getMvvmView().showBannerView(banner);
        }
    }

    public class EntranceFootViewModel extends BaseViewModel {

        public final ObservableList<LiveEntranceIcons> items = new ObservableArrayList<>();

        public EntranceFootViewModel(List<LiveEntranceIcons> entranceIconses) {
            items.clear();
            items.addAll(entranceIconses);
        }
    }

    public class EntranceIconViewModel extends BaseViewModel {

        public final ObservableField<LiveEntranceIcons> entranceIcon = new ObservableField<>();

        public EntranceIconViewModel(LiveEntranceIcons liveEntranceIcons) {
            entranceIcon.set(liveEntranceIcons);
        }

        public void onClickItem(LiveEntranceIcons entranceIcons) {
            LiveViewModel.this.getMvvmView().showEntranceView(entranceIcons);
        }
    }

    @SuppressWarnings("deprecation")
    public class PartitionHeadViewModel extends BaseViewModel {

        public final ObservableField<LivePartition> partition = new ObservableField<>();
        public final ObservableField<CharSequence> currentLive = new ObservableField<>();

        public PartitionHeadViewModel(LivePartition livePartition) {
            partition.set(livePartition);

            String text = String.format(mContext.getString(R.string.current_live),
                    new Random().nextInt(10000));
            currentLive.set(Html.fromHtml(text));
        }
    }

    public class PartitionBodyViewModel extends BaseViewModel {

        public final ObservableField<LiveInfo> live = new ObservableField<>();

        public PartitionBodyViewModel(LiveInfo liveInfo) {
            live.set(liveInfo);
        }

        public void onClickItem(LiveInfo live) {
            LiveViewModel.this.getMvvmView().showLiveView(live);
        }
    }

}
