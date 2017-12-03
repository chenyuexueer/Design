package com.aib.net;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/28.
 */

public class HttpApi  {

    public static final String baseUrl = ""; //外网
    public static final String baseURL = "http://192.168.2.98:8080/tianyiApp/"; //内网
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static String session = "";
    public static final String COOKIE = "Cookie";
    public static final String OK = "OK";
    public static  final String RESULT_OK = "200";
    public static  final String RESULT_FAIL = "500";
    public static final String NO_LOGIN_TIP = "您还没有登录，请先登录";
    public static final String ERROR="操作失败,系统正在努力的维护.";

        //MasklayerView
    public static  void POSTDATA(MyObserver myObserver, String methodName, Object[] params, Class<?>... paramsType) {
        Log.i("HttpApi","参数：=="+ Arrays.toString(params));
        try{
            ApiService apiService = RetrofitUtil.getApi();
            Method method =  apiService.getClass().getMethod(methodName,paramsType);
            Observable<String> observer  = (Observable<String>)method.invoke(apiService,params);
            observer.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(myObserver);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void setSession(String sessionStr) {
        String[] sessionArr = sessionStr.split(",");
        session = sessionArr[0];
    }
}
