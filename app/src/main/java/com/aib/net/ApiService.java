package com.aib.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface ApiService {
    @POST("TeacherRegister")
    @FormUrlEncoded
    Call<ResponseBody> teacherRegister();
}

