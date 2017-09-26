package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.BangumiInfoEntity;
import com.sunny.mvvmbilibili.data.model.entity.BangumiRecommendEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface BangumiService {

    String ENDPOINT = "http://bangumi.bilibili.com/";

    @GET("api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios")
    Observable<BangumiInfoEntity> getBangumiInfo();

    @GET("api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558")
    Observable<BangumiRecommendEntity> getBangumiRecommend();

}
