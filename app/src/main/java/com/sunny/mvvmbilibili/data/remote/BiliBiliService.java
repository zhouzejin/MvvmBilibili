package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.RecommendBannerEntity;
import com.sunny.mvvmbilibili.data.model.entity.RecommendShowEntity;
import com.sunny.mvvmbilibili.data.model.entity.SearchArchiveEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

public interface BiliBiliService {

    String ENDPOINT = "http://app.bilibili.com/";

    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<RecommendShowEntity> getRecommendShow();

    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<RecommendBannerEntity> getRecommendBanner();

    @GET("x/v2/search?actionKey=appkey&appkey=27eb53fc9058f8c3&build=3710&device=phone&duration=0&mobi_app=iphone&order=default&platform=ios&rid=0")
    Observable<SearchArchiveEntity> searchArchive(@Query("keyword") String keyword, @Query("pn") int pageNum);

}
