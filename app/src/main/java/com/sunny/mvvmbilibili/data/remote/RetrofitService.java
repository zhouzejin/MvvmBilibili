package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.InTheatersEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {

    String ENDPOINT = "https://api.douban.com/v2/";

    @GET("movie/in_theaters")
    Observable<InTheatersEntity> getSubjects();

}
