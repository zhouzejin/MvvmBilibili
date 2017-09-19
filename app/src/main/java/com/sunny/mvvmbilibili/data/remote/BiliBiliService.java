package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.RecommendBannerEntity;
import com.sunny.mvvmbilibili.data.model.entity.RecommendShowEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Zhou Zejin on 2017/9/11.
 */

public interface BiliBiliService {

    String ENDPOINT = "http://app.bilibili.com/";

    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<RecommendShowEntity> getRecommendShow();

    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<RecommendBannerEntity> getRecommendBanner();

}
