package com.kermitye.newye;

import com.kermitye.yesdk.bean.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by kermitye
 * Date: 2018/5/28 11:34
 * Desc:
 */
public interface Api {
    String HOST = "http://192.168.1.116:3000/";

    @GET("users")
    Observable<Response<List<User>>> getUsers();

    @GET("test")
    Observable<Response<User>> getUser();
}
