package com.ln.https;

import com.ln.entity.JokeListBean;
import com.ln.entity.LoginInBean;

import java.util.Map;

import okhttp3.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by linan   on 2017/1/4.
 */

public interface APIS {

    @FormUrlEncoded
    @POST("a/login")
    Observable<LoginInBean> loginIn(@FieldMap Map<String, String> map);

    @GET("http://japi.juhe.cn/joke/content/list.from")
    Observable<JokeListBean> getJokeList(@QueryMap Map<String,Object>map);

}
