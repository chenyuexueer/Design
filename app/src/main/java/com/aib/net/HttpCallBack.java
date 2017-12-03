package com.aib.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HLP on 2017/11/9.
 */

public abstract class HttpCallBack<T> implements Callback<T> {
    public HttpCallBack() {
        onStart();
    }

    public abstract void onStart();

    public abstract void onResponse(Call<T> call, Response<T> response);

    public abstract void onFailure(Call<T> call, Throwable t);
}
