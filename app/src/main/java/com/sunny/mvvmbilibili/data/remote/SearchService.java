package com.sunny.mvvmbilibili.data.remote;

import com.sunny.mvvmbilibili.data.model.entity.HotWordEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Zhou Zejin on 2017/10/18.
 */

public interface SearchService {

    String ENDPOINT = "http://s.search.bilibili.com/";

    @GET("main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3")
    Observable<HotWordEntity> getSearchHotWord();

}
