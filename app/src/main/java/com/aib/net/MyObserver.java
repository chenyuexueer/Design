package com.aib.net;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import rx.Observer;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.untitled.runningfood.base.BaseActivity;
//import com.untitled.runningfood.base.DesignApplication;
//import com.untitled.runningfood.bean.Entity;
//import com.untitled.runningfood.bean.OuterEntity;
//import com.untitled.runningfood.ui.activity.SignActivity;
//import com.untitled.runningfood.util.SPUtils;
//import com.untitled.runningfood.weight.MasklayerView;
//import com.ypy.eventbus.EventBus;
//
//import okhttp3.ResponseBody;
//import rx.Observer;


/**
 * Created by caopenghui on 2017-01-03.
 */
public class MyObserver<T> implements Observer<T>  {

    private CallBack callBack;

    private Dialog mDialog;

    private Context mContext;

    private Gson gson = new Gson();


    @Override
    public void onCompleted() {
        Log.i("MyObserver","onCompleted()");
        if(mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.i("MyObserver","onError()");
        if(mDialog!=null){
            mDialog.dismiss();
            mDialog=null;
        }
        e.printStackTrace();
    }

    //rxjava
    @Override
    public void onNext(T t) {
        if(callBack!=null){
            callBack.callBack(t);
        }

    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack<T>{
        void  callBack(T entity);
    }
}
