package com.sunny.mvvmbilibili.ui.home.region;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.data.model.pojo.Region;
import com.sunny.mvvmbilibili.injection.qualifier.ApplicationContext;
import com.sunny.mvvmbilibili.injection.scope.ConfigPersistent;
import com.sunny.mvvmbilibili.ui.base.BaseViewModel;
import com.sunny.mvvmbilibili.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Region view model.
 * Created by Zhou Zejin on 2017/9/26.
 */

@ConfigPersistent
public class RegionViewModel extends BaseViewModel<RegionMvvmView> {

    private final static Integer[] iconRes = new Integer[]{
            R.drawable.ic_category_live,
            R.drawable.ic_category_bangumi,
            R.drawable.ic_category_animation,
            R.drawable.ic_category_music,
            R.drawable.ic_category_dance,
            R.drawable.ic_category_game,
            R.drawable.ic_category_science,
            R.drawable.ic_category_life,
            R.drawable.ic_category_ghost,
            R.drawable.ic_category_fashion,
            R.drawable.ic_category_ad,
            R.drawable.ic_category_recreation,
            R.drawable.ic_category_film,
            R.drawable.ic_category_tv,
            R.drawable.ic_category_gamecenter,
    };

    public final ObservableList<Region> items = new ObservableArrayList<>();

    private final @ApplicationContext Context mContext;

    private Disposable mDisposable;

    @Inject
    public RegionViewModel(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public void attachView(RegionMvvmView mvvmView) {
        super.attachView(mvvmView);
    }

    @Override
    public void detachView() {
        RxUtil.dispose(mDisposable);
        super.detachView();
    }

    public void loadData() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        Observable.just(mContext.getResources().getStringArray(R.array.region_title))
                .subscribeOn(Schedulers.io())
                .map(new Function<String[], List<Region>>() {
                    @Override
                    public List<Region> apply(@NonNull String[] strings) throws Exception {
                        List<Region> regions = new ArrayList<>();
                        for (int i = 0; i < strings.length; i++) {
                            Region region = Region.create(iconRes[i], strings[i]);
                            regions.add(region);
                        }
                        return regions;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showProgress();
                        mDisposable = disposable;
                    }
                })
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<List<Region>>() {
                    @Override
                    public void accept(List<Region> regions) throws Exception {
                        hideProgress();
                        items.clear();
                        items.addAll(regions);
                    }
                });
    }

    /*****
     * Inner ViewModel
     *****/

    public class RegionItemViewModel extends BaseViewModel {

        public final ObservableField<Region> region = new ObservableField<>();

        public RegionItemViewModel(Region reg) {
            region.set(reg);
        }

        public void onClickItem(Region region) {
            RegionViewModel.this.getMvvmView().showRegionView(region);
        }
    }

}
