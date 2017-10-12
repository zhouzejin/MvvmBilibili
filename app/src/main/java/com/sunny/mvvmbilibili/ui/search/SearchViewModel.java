package com.sunny.mvvmbilibili.ui.search;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Parcelable;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.DataManager;
import com.sunny.mvvmbilibili.data.model.bean.SearchData;
import com.sunny.mvvmbilibili.data.model.bean.SearchNav;
import com.sunny.mvvmbilibili.data.model.pojo.SearchArchive;
import com.sunny.mvvmbilibili.data.model.pojo.SearchMovie;
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
 * The type Search view model.
 * Created by Zhou Zejin on 2017/9/27.
 */
@ConfigPersistent
public class SearchViewModel extends BaseViewModel<SearchMvvmView> {

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
    public final ObservableList<SearchNav> searchNavs = new ObservableArrayList<>();
    public final ObservableList<Parcelable> items = new ObservableArrayList<>();
    public final ObservableField<Boolean> isSearching = new ObservableField<>();
    public final ObservableField<Boolean> isShowContent = new ObservableField<>();

    private final Context mContext;
    private final DataManager mDataManager;

    private Disposable mDisposable;

    @Inject
    public SearchViewModel(@ApplicationContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchMvvmView mvvmView) {
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
        mDataManager.searchArchive(queryStr, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;

                        isSearching.set(true);
                        if (pageNum > 1) { // 加载下一页
                            searchFooterLayout.loadHint.set("");
                            getMvvmView().setRecyclerScrollLoading(true);
                        } else {
                            getMvvmView().showSearching();
                            isShowContent.set(false);
                            contentEmptyLayout.isShowContentEmpty.set(false);
                        }
                    }
                })
                .subscribe(new Observer<SearchData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull SearchData searchData) {
                        isSearching.set(false);
                        if (pageNum > 1) { // 加载下一页
                            if (searchData.items().archive() != null) {
                                items.addAll(searchData.items().archive());
                                getMvvmView().setRecyclerScrollLoading(false);
                            } else {
                                searchFooterLayout.loadHint.set(mContext.getString(R.string.load_over));
                                getMvvmView().setRecyclerScrollLoading(true); // 加载完毕，不再加载
                            }
                        } else {
                            searchNavs.clear();
                            searchNavs.addAll(searchData.nav());
                            items.clear();
                            if (searchData.items().season() != null)
                                items.addAll(searchData.items().season());
                            if (searchData.items().movie() != null)
                                items.addAll(searchData.items().movie());
                            if (searchData.items().archive() != null)
                                items.addAll(searchData.items().archive());
                            getMvvmView().showSearchNav();

                            getMvvmView().hideSearching();
                            isShowContent.set(true);
                            contentEmptyLayout.isShowContentEmpty.set(false);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e, "There was an error searching the Archive.");

                        isSearching.set(false);
                        if (pageNum > 1) { // 加载下一页
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

    public class SearchMovieViewModel extends BaseViewModel {

        public final ObservableField<SearchMovie> movie = new ObservableField<>();

        public SearchMovieViewModel(SearchMovie searchMovie) {
            movie.set(searchMovie);
        }
    }

    public class SearchArchiveViewModel extends BaseViewModel {

        public final ObservableField<SearchArchive> archive = new ObservableField<>();

        public SearchArchiveViewModel(SearchArchive searchArchive) {
            archive.set(searchArchive);
        }
    }

}
