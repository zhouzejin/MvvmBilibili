package com.sunny.mvvmbilibili.ui.search.bangumi;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.SearchBangumiData;
import com.sunny.mvvmbilibili.data.model.pojo.SearchSeason;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.ui.layout.ContentEmptyLayout;
import com.sunny.mvvmbilibili.ui.layout.SearchFooterLayout;
import com.sunny.mvvmbilibili.utils.LogUtil;
import com.sunny.mvvmbilibili.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Search bangumi view model.
 * Created by Zhou Zejin on 2017/10/13.
 */
@ConfigPersistent
public class SearchBangumiViewModel extends BaseViewModel<SearchBangumiMvvmView> {

    public final ContentEmptyLayout contentEmptyLayout = new ContentEmptyLayout() {
        @Override
        public int getContentEmptyImg() {
            return R.drawable.img_search_empty;
        }

        @Override
        public int getContentEmptyHint() {
            return R.string.space;
        }
    };
    public final SearchFooterLayout searchFooterLayout = new SearchFooterLayout();
    // These observable fields will update Views automatically
    public final ObservableField<Boolean> isSearching = new ObservableField<>();
    public final ObservableField<Boolean> isShowContent = new ObservableField<>();
    public final ObservableList<SearchSeason> items = new ObservableArrayList<>();

    private final Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public SearchBangumiViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchBangumiMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        super.detachView();
        RxUtil.dispose(mDisposable);
    }

    public void search(String queryStr, final int pageNum) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.searchBangumi(queryStr, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;

                        if (pageNum > 1) {
                            searchFooterLayout.loadHint.set("");
                            getMvvmView().setRecyclerScrollLoading(true);
                        } else {
                            getMvvmView().showSearching();
                            isShowContent.set(false);
                            contentEmptyLayout.isShowContentEmpty.set(false);
                        }
                    }
                })
                .subscribe(new Observer<SearchBangumiData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull SearchBangumiData searchBangumiData) {
                        if (pageNum > 1) {
                            if (searchBangumiData.items() != null) {
                                items.addAll(searchBangumiData.items());
                                getMvvmView().setRecyclerScrollLoading(false);
                            } else {
                                searchFooterLayout.loadHint.set(mContext.getString(R.string.load_over));
                                getMvvmView().setRecyclerScrollLoading(true); // 加载完毕，不再加载
                            }
                        } else {
                            items.clear();
                            if (searchBangumiData.items() != null)
                                items.addAll(searchBangumiData.items());

                            getMvvmView().hideSearching();
                            if (items.isEmpty()) {
                                isShowContent.set(false);
                                contentEmptyLayout.isShowContentEmpty.set(true);
                            } else {
                                isShowContent.set(true);
                                contentEmptyLayout.isShowContentEmpty.set(false);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error searching the Bangumi.");

                        if (pageNum > 1) {
                            searchFooterLayout.loadHint.set(mContext.getString(R.string.load_error));
                            getMvvmView().setCurrentPage(pageNum - 1); // 加载失败，继续加载该页
                            getMvvmView().setRecyclerScrollLoading(false);
                        } else {
                            getMvvmView().hideSearching();
                            isShowContent.set(false);
                            contentEmptyLayout.isShowContentEmpty.set(true);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*****
     * Inner ViewModel
     *****/

    public class SearchSeasonViewModel extends BaseViewModel {

        public final ObservableField<SearchSeason> season = new ObservableField<>();

        public SearchSeasonViewModel(SearchSeason searchSeason) {
            season.set(searchSeason);
        }
    }

}
