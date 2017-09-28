package com.sunny.mvvmbilibili.ui.home.recommend;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.RecommendBanner;
import com.sunny.mvvmbilibili.data.model.bean.RecommendResult;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendBody;
import com.sunny.mvvmbilibili.data.model.pojo.RecommendHead;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ContentEmptyLayout;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;
import com.sunny.mvvmbilibili.utils.imageloader.ImageLoader;

import java.util.List;
import java.util.Random;

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
 * The type Recommend view model.
 * Created by Zhou zejin on 2017/9/8.
 */

@ConfigPersistent
public class RecommendViewModel extends BaseViewModel<RecommendMvvmView> {

    public final ContentEmptyLayout contentEmptyLayout = new ContentEmptyLayout() {
        @Override
        public int getContentEmptyImg() {
            return R.drawable.img_load_error;
        }

        @Override
        public int getContentEmptyHint() {
            return R.string.load_error;
        }
    };

    // These observable fields will update Views automatically
    public final ObservableField<Boolean> isRefreshing = new ObservableField<>();
    public final ObservableList<RecommendBanner> banners = new ObservableArrayList<>();
    public final ObservableList<RecommendResult> results = new ObservableArrayList<>();

    private final @ApplicationContext Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public RecommendViewModel(ImageLoader imageLoader, @ApplicationContext Context context,
                              DataManager dataManager) {
        sImageLoader = imageLoader;
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RecommendMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        RxUtil.dispose(mDisposable);
        super.detachView();
    }

    @Bindable
    public @ColorRes int[] getColorSchemeResources() {
        return new int[]{R.color.primary};
    }

    public void refresh() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getRecommendBanners()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                        isRefreshing.set(true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<RecommendBanner>>() {
                    @Override
                    public void accept(List<RecommendBanner> recommendBanners) throws Exception {
                        banners.clear();
                        banners.addAll(recommendBanners);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<RecommendBanner>, ObservableSource<List<RecommendResult>>>() {
                    @Override
                    public ObservableSource<List<RecommendResult>> apply(@NonNull List<RecommendBanner> recommendBanners) throws Exception {
                        return mDataManager.getRecommendResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RecommendResult>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<RecommendResult> recommendResults) {
                        isRefreshing.set(false);
                        contentEmptyLayout.isShowContentEmpty.set(false);
                        results.clear();
                        results.addAll(recommendResults);
                        getMvvmView().showRecommendInfo();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error loading the RecommendInfo.");
                        isRefreshing.set(false);
                        if (banners.isEmpty() && results.isEmpty())
                            contentEmptyLayout.isShowContentEmpty.set(true);
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

        public final ObservableField<RecommendBanner> banner = new ObservableField<>();

        public BannerViewModel(RecommendBanner recommendBanner) {
            banner.set(recommendBanner);
        }

        public void onClickItem(RecommendBanner banner) {
            RecommendViewModel.this.getMvvmView().showBannerInfo(banner);
        }
    }

    public class ResultBodyViewModel extends BaseViewModel {

        public final ObservableField<RecommendBody> body = new ObservableField<>();

        public ResultBodyViewModel(RecommendBody recommendBody) {
            body.set(recommendBody);
        }

        public void onClickItem(RecommendBody body) {
            RecommendViewModel.this.getMvvmView().showResultBodyInfo(body);
        }
    }

    @SuppressWarnings("deprecation")
    public class ResultHeadViewModel extends BaseViewModel {

        public final ObservableField<RecommendHead> head = new ObservableField<>();
        public final ObservableField<Integer> icon = new ObservableField<>();
        public final ObservableField<CharSequence> currentLive = new ObservableField<>();

        public ResultHeadViewModel(RecommendHead recommendHead, @DrawableRes Integer recommendIcon) {
            head.set(recommendHead);
            icon.set(recommendIcon);

            String text = String.format(mContext.getString(R.string.current_live),
                    new Random().nextInt(10000));
            currentLive.set(Html.fromHtml(text));
        }

        public void onClickRankView() {
            RecommendViewModel.this.getMvvmView().showRankView();
        }
    }

    public class ResultFootViewModel extends BaseViewModel {

        public final ObservableField<CharSequence> dynamic = new ObservableField<>();
        public final ObservableList<RecommendBody> items = new ObservableArrayList<>();

        public ResultFootViewModel() {
            String text = String.format(mContext.getString(R.string.recommend_dynamic),
                    new Random().nextInt(1000));
            dynamic.set(text);
        }

        public ResultFootViewModel(List<RecommendBody> recommendBodies) {
            items.clear();
            items.addAll(recommendBodies);
        }

        public void onClickMoreView(View v) {
            v.clearAnimation();
            Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_more);
            v.startAnimation(animation);
        }

        public void onClickTimelineView() {
            RecommendViewModel.this.getMvvmView().showTimelineView();
        }

        public void onClickIndexView() {
            RecommendViewModel.this.getMvvmView().showIndexView();
        }
    }

}
