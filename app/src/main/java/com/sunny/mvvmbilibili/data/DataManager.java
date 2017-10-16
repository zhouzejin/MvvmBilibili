package com.sunny.mvvmbilibili.data;

import com.sunny.mvvmbilibili.data.local.DatabaseHelper;
import com.sunny.mvvmbilibili.data.local.FileHelper;
import com.sunny.mvvmbilibili.data.local.PreferencesHelper;
import com.sunny.mvvmbilibili.data.model.bean.BangumiRecommendResult;
import com.sunny.mvvmbilibili.data.model.bean.BangumiResult;
import com.sunny.mvvmbilibili.data.model.bean.GameInfo;
import com.sunny.mvvmbilibili.data.model.bean.LiveInfos;
import com.sunny.mvvmbilibili.data.model.bean.RecommendBanner;
import com.sunny.mvvmbilibili.data.model.bean.RecommendResult;
import com.sunny.mvvmbilibili.data.model.bean.SearchBangumiData;
import com.sunny.mvvmbilibili.data.model.bean.SearchData;
import com.sunny.mvvmbilibili.data.model.bean.SearchMovieData;
import com.sunny.mvvmbilibili.data.model.bean.SearchUpperData;
import com.sunny.mvvmbilibili.data.model.bean.Subject;
import com.sunny.mvvmbilibili.data.model.bean.VipGameInfo;
import com.sunny.mvvmbilibili.data.model.entity.BangumiInfoEntity;
import com.sunny.mvvmbilibili.data.model.entity.BangumiRecommendEntity;
import com.sunny.mvvmbilibili.data.model.entity.GameInfoEntity;
import com.sunny.mvvmbilibili.data.model.entity.InTheatersEntity;
import com.sunny.mvvmbilibili.data.model.entity.LiveInfoEntity;
import com.sunny.mvvmbilibili.data.model.entity.RecommendBannerEntity;
import com.sunny.mvvmbilibili.data.model.entity.RecommendShowEntity;
import com.sunny.mvvmbilibili.data.model.entity.SearchArchiveEntity;
import com.sunny.mvvmbilibili.data.model.entity.SearchBangumiEntity;
import com.sunny.mvvmbilibili.data.model.entity.SearchMovieEntity;
import com.sunny.mvvmbilibili.data.model.entity.SearchUpperEntity;
import com.sunny.mvvmbilibili.data.model.entity.VipGameInfoEntity;
import com.sunny.mvvmbilibili.data.remote.RetrofitHelper;
import com.sunny.mvvmbilibili.utils.factory.MyGsonTypeAdapterFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Singleton
public class DataManager {

    private final PreferencesHelper mPreferencesHelper;
    private final FileHelper mFileHelper;
    private final DatabaseHelper mDatabaseHelper;
    private final RetrofitHelper mRetrofitHelper;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper, FileHelper fileHelper,
                       DatabaseHelper databaseHelper, RetrofitHelper retrofitHelper) {
        mPreferencesHelper = preferencesHelper;
        mFileHelper = fileHelper;
        mDatabaseHelper = databaseHelper;
        mRetrofitHelper = retrofitHelper;
    }

    /*****
     * Preferences Data Source
     *****/

    public void setLogin(Boolean isLogin) {
        mPreferencesHelper.putBoolean(PreferencesHelper.KEY_IS_LOGIN, isLogin);
    }

    public Boolean isLogin() {
        return mPreferencesHelper.getBoolean(PreferencesHelper.KEY_IS_LOGIN, false);
    }

    /*****
     * File Data Source
     *****/
    public Observable<List<GameInfo>> getGameInfo() {
        return Observable.create(new ObservableOnSubscribe<List<GameInfo>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<GameInfo>> e) throws Exception {
                if (e.isDisposed()) return;
                String content = mFileHelper.readAssetsFile("game_info.json");
                GameInfoEntity entity = MyGsonTypeAdapterFactory.getRegisterTypeGson()
                        .fromJson(content, GameInfoEntity.class);
                if (entity != null && entity.items() != null) {
                    e.onNext(entity.items());
                    e.onComplete();
                } else {
                    e.onError(new RuntimeException("Read game info data error"));
                }
            }
        });
    }

    /*****
     * Database Data Source
     *****/

    public Observable<List<Subject>> getSubjects() {
        return mDatabaseHelper.getSubjects().distinct();
    }

    /*****
     * Net Data Source
     *****/

    public Observable<Subject> syncSubjects() {
        return mRetrofitHelper.getRetrofitService()
                .getSubjects()
                .concatMap(new Function<InTheatersEntity, ObservableSource<? extends Subject>>() {
                    @Override
                    public ObservableSource<? extends Subject> apply(@NonNull InTheatersEntity inTheatersEntity) throws Exception {
                        return mDatabaseHelper.setSubjects(inTheatersEntity.subjects());
                    }
                });
    }

    public Observable<VipGameInfo> getVipGameInfo() {
        return mRetrofitHelper.getVipService()
                .getVipGame()
                .map(new Function<VipGameInfoEntity, VipGameInfo>() {
                    @Override
                    public VipGameInfo apply(@NonNull VipGameInfoEntity vipGameInfoEntity) throws Exception {
                        return vipGameInfoEntity.data();
                    }
                });
    }

    public Observable<List<RecommendBanner>> getRecommendBanners() {
        return mRetrofitHelper.getBiliBiliService()
                .getRecommendBanner()
                .map(new Function<RecommendBannerEntity, List<RecommendBanner>>() {
                    @Override
                    public List<RecommendBanner> apply(@NonNull RecommendBannerEntity recommendBannerEntity) throws Exception {
                        return recommendBannerEntity.data();
                    }
                });
    }

    public Observable<List<RecommendResult>> getRecommendResults() {
        return mRetrofitHelper.getBiliBiliService()
                .getRecommendShow()
                .map(new Function<RecommendShowEntity, List<RecommendResult>>() {
                    @Override
                    public List<RecommendResult> apply(@NonNull RecommendShowEntity recommendShowEntity) throws Exception {
                        return recommendShowEntity.result();
                    }
                });
    }

    public Observable<LiveInfos> getLiveInfos() {
        return mRetrofitHelper.getLiveService()
                .getLiveInfo()
                .map(new Function<LiveInfoEntity, LiveInfos>() {
                    @Override
                    public LiveInfos apply(@NonNull LiveInfoEntity liveInfoEntity) throws Exception {
                        return liveInfoEntity.data();
                    }
                });
    }

    public Observable<BangumiResult> getBangumiInfo() {
        return mRetrofitHelper.getBangumiService()
                .getBangumiInfo()
                .map(new Function<BangumiInfoEntity, BangumiResult>() {
                    @Override
                    public BangumiResult apply(@NonNull BangumiInfoEntity bangumiInfoEntity) throws Exception {
                        return bangumiInfoEntity.result();
                    }
                });
    }

    public Observable<List<BangumiRecommendResult>> getBangumiRecommend() {
        return mRetrofitHelper.getBangumiService()
                .getBangumiRecommend()
                .map(new Function<BangumiRecommendEntity, List<BangumiRecommendResult>>() {
                    @Override
                    public List<BangumiRecommendResult> apply(@NonNull BangumiRecommendEntity bangumiRecommendEntity) throws Exception {
                        return bangumiRecommendEntity.result();
                    }
                });
    }

    public Observable<SearchData> searchArchive(String keyword, int pageNum) {
        return mRetrofitHelper.getBiliBiliService()
                .searchArchive(keyword, pageNum)
                .map(new Function<SearchArchiveEntity, SearchData>() {
                    @Override
                    public SearchData apply(@NonNull SearchArchiveEntity searchArchiveEntity) throws Exception {
                        return searchArchiveEntity.data();
                    }
                });
    }

    public Observable<SearchBangumiData> searchBangumi(String keyword, int pageNum) {
        return mRetrofitHelper.getBiliBiliService()
                .searchBangumi(keyword, pageNum)
                .map(new Function<SearchBangumiEntity, SearchBangumiData>() {
                    @Override
                    public SearchBangumiData apply(@NonNull SearchBangumiEntity searchBangumiEntity) throws Exception {
                        return searchBangumiEntity.data();
                    }
                });
    }

    public Observable<SearchMovieData> searchMovie(String keyword, int pageNum) {
        return mRetrofitHelper.getBiliBiliService()
                .searchMovie(keyword, pageNum)
                .map(new Function<SearchMovieEntity, SearchMovieData>() {
                    @Override
                    public SearchMovieData apply(@NonNull SearchMovieEntity searchMovieEntity) throws Exception {
                        return searchMovieEntity.data();
                    }
                });
    }

    public Observable<SearchUpperData> searchUpper(String keyword, int pageNum) {
        return mRetrofitHelper.getBiliBiliService()
                .searchUpper(keyword, pageNum)
                .map(new Function<SearchUpperEntity, SearchUpperData>() {
                    @Override
                    public SearchUpperData apply(@NonNull SearchUpperEntity searchUpperEntity) throws Exception {
                        return searchUpperEntity.data();
                    }
                });
    }

}
