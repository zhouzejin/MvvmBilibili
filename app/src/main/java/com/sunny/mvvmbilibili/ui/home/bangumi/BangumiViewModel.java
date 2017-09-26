package com.sunny.mvvmbilibili.ui.home.bangumi;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.BangumiAd;
import com.sunny.mvvmbilibili.data.model.bean.BangumiPrevious;
import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;
import com.sunny.mvvmbilibili.data.model.bean.BangumiResult;
import com.sunny.mvvmbilibili.data.model.bean.BangumiSerializing;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiBody;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiHead;
import com.sunny.mvvmbilibili.data.model.pojo.BangumiList;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;

import java.util.List;

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
 * The type Bangumi view model.
 * Created by Zhou Zejin on 2017/9/25.
 */

@ConfigPersistent
public class BangumiViewModel extends BaseViewModel<BangumiMvvmView> {

    // These observable fields will update Views automatically
    public final ObservableField<Boolean> isRefreshing = new ObservableField<>();
    public final ObservableField<BangumiAd> bangumiAd = new ObservableField<>();
    public final ObservableField<BangumiPrevious> bangumiPrevious = new ObservableField<>();
    public final ObservableList<BangumiSerializing> bangumiSerializings = new ObservableArrayList<>();
    public final ObservableList<BangumiRecommendResult> recommendResults = new ObservableArrayList<>();

    private final @ApplicationContext Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public BangumiViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(BangumiMvvmView mvvmView) {
        RxUtil.dispose(mDisposable);
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
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

    @Bindable
    public @ColorRes int[] getColorSchemeResources() {
        return new int[]{R.color.primary};
    }

    public void refresh() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getBangumiInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BangumiResult>() {
                    @Override
                    public void accept(BangumiResult bangumiResult) throws Exception {
                        bangumiAd.set(bangumiResult.ad());
                        bangumiPrevious.set(bangumiResult.previous());
                        bangumiSerializings.clear();
                        bangumiSerializings.addAll(bangumiResult.serializing());
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<BangumiResult, ObservableSource<List<BangumiRecommendResult>>>() {
                    @Override
                    public ObservableSource<List<BangumiRecommendResult>> apply(@NonNull BangumiResult bangumiResult) throws Exception {
                        return mDataManager.getBangumiRecommend();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                        isRefreshing.set(true);
                    }
                })
                .subscribe(new Observer<List<BangumiRecommendResult>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<BangumiRecommendResult> bangumiRecommendResults) {
                        isRefreshing.set(false);
                        isShowContentEmpty.set(false);
                        recommendResults.clear();
                        recommendResults.addAll(bangumiRecommendResults);
                        getMvvmView().showBangumiInfo();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error loading the BangumiInfo.");
                        isRefreshing.set(false);
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

    public class BangumiHeadViewModel extends BaseViewModel {

        public final ObservableField<BangumiHead> bangumiHead = new ObservableField<>();

        public BangumiHeadViewModel(BangumiHead head) {
            bangumiHead.set(head);
        }

        public void onClickItem(BangumiHead head) {
            BangumiViewModel.this.getMvvmView().showBangumiHeadView(head);
        }
    }

    public class SerializingBodyViewModel extends BaseViewModel {

        public final ObservableField<BangumiSerializing> bangumiSerializing = new ObservableField<>();
        public final ObservableField<String> currentWatching = new ObservableField<>();
        public final ObservableField<String> updatedTo = new ObservableField<>();

        public SerializingBodyViewModel(BangumiSerializing serializing) {
            bangumiSerializing.set(serializing);

            String temp = mContext.getString(R.string.current_watching, serializing.watching_count());
            currentWatching.set(temp);
            temp = mContext.getString(R.string.updated_to, serializing.newest_ep_index());
            updatedTo.set(temp);
        }

        public void onClickItem(BangumiSerializing serializing) {
            BangumiViewModel.this.getMvvmView().showSerializingBodyView(serializing);
        }
    }

    public class BangumiBodyViewModel extends BaseViewModel {

        public final ObservableField<BangumiBody> bangumiBody = new ObservableField<>();

        public BangumiBodyViewModel(BangumiBody body) {
            bangumiBody.set(body);
        }

        public void onClickItem(BangumiBody body) {
            BangumiViewModel.this.getMvvmView().showBangumiBodyView(body);
        }
    }

    public class PreviousHeaderViewModel extends BaseViewModel {

        public final ObservableField<Integer> icon = new ObservableField<>();
        public final ObservableField<String> title = new ObservableField<>();

        public PreviousHeaderViewModel(@DrawableRes Integer previousIcon, String previousTitle) {
            icon.set(previousIcon);
            title.set(previousTitle);
        }
    }

    public class PreviousBodyViewModel extends BaseViewModel {

        public final ObservableField<BangumiList> bangumiList = new ObservableField<>();
        public final ObservableField<String> currentpursuing = new ObservableField<>();

        public PreviousBodyViewModel(BangumiList list) {
            bangumiList.set(list);

            String temp = mContext.getString(R.string.current_pursuing, list.favourites());
            currentpursuing.set(temp);
        }

        public void onClickItem(BangumiList list) {
            BangumiViewModel.this.getMvvmView().showBangumiListView(list);
        }
    }

    public class ResultBodyViewModel extends BaseViewModel {

        public final ObservableField<BangumiRecommendResult> result = new ObservableField<>();
        public final ObservableField<Boolean> isNew = new ObservableField<>();

        public ResultBodyViewModel(BangumiRecommendResult bangumiRecommendResult) {
            result.set(bangumiRecommendResult);
            if (bangumiRecommendResult.is_new() == 1) {
                isNew.set(true);
            } else {
                isNew.set(false);
            }
        }

        public void onClickItem(BangumiRecommendResult result) {
            BangumiViewModel.this.getMvvmView().showRecommendResultView(result);
        }
    }

}
