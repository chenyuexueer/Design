package com.aib.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.aib.base.DesignApplication;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lam on 2017/7/17 0009.
 */

public class RetrofitUtil {
    public static Retrofit retrofit = null;
    public static ApiService api;

    public static Retrofit retrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.baseURL)
                    .client(getClient(DesignApplication.getContext()))
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    /**
     * @return API地址
     */
    public static ApiService getApi() {
        if (api == null) {
            api = retrofit().create(ApiService.class);
        }
        return api;
    }


    /**
     * @param pathList  图片的字符串地址
     * @param mediaType 文件的类型 MediaType.parse("image/png")
     */
    public static Map<String, RequestBody> uploadFile(ArrayList<String> pathList, MediaType mediaType) {

        Map<String, RequestBody> bodyMap = new HashMap<>();
        if (pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
                bodyMap.put("file\"; filename=\"" + file.getName(), RequestBody.create(mediaType, file));
            }
        }
        return bodyMap;
    }

    //获取cookies
    public static class ReceivedCookiesInterceptor implements Interceptor {
        private Context context;

        public ReceivedCookiesInterceptor() {
            super();
            this.context = DesignApplication.getContext();
        }

        @Override
        public Response intercept(Chain chain) throws IOException {

            Response originalResponse = chain.proceed(chain.request());
            //这里获取请求返回的cookie
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                final StringBuffer cookieBuffer = new StringBuffer();
                Observable.from(originalResponse.headers("Set-Cookie"))
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                String[] cookieArray = s.split(";");
                                return cookieArray[0];
                            }
                        })
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String cookie) {
                                cookieBuffer.append(cookie).append(";");
                            }
                        });
                SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cookie", cookieBuffer.toString());

                editor.commit();
            }

            return originalResponse;
        }

    }

    //请求头加cookies
    public static class AddCookiesInterceptor implements Interceptor {
        private Context context;

        public AddCookiesInterceptor() {
            super();
            this.context = DesignApplication.getContext();

        }

        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request.Builder builder = chain.request().newBuilder();
            SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            Observable.just(sharedPreferences.getString("cookie", ""))
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            //添加cookie
                            builder.addHeader("Cookie", cookie);
                            //   Log.e("cooking",cookie);
                        }
                    });
            return chain.proceed(builder.build());
        }
    }

    public static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         *设置缓存
         */
        File cacheFile = new File(DesignApplication.getContext().getExternalCacheDir(), "aib");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtils.isConnected(DesignApplication.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);

                //存入Session
                if (response.header("Set-Cookie") != null) {
                    HttpApi.setSession(response.header("Set-Cookie"));
                }
                return response;
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        builder.addInterceptor(new ReceivedCookiesInterceptor());
        builder.addInterceptor(new AddCookiesInterceptor());

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        /**
         * 设置超时和重连
         */

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        //以上设置结束，才能build(),不然设置白搭
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }
}