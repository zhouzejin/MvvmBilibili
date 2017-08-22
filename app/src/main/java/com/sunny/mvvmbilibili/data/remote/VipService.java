package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.VipGameInfoEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface VipService {

    String ENDPOINT = "http://vip.bilibili.com/";

    @GET("api/v1/games/gift?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=f6a995f30f3d4362a628191d523e3012&ts=1477922329")
    Observable<VipGameInfoEntity> getVipGame();

}
