package com.aib.net;

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

    /**
     * 注册
     *
     * @param phone
     * @param pwd
     * @param sex
     * @return
     */
//    @POST("user/register.do")
//    @FormUrlEncoded
//    Call<RegisterBean> register(@Field("userNum") String phone, @Field("userPassword") String pwd, @Field("userAccCode") int invitationCode, @Field("userSex") int sex);
    /**
     * 钱包 优惠券 多少
     *
     * @param userId 用户ID
     * @return
     */
//    @POST("personal/getWalletAndCouponByuserId.do")
//    Observable<MineBean> getWalletAndCoupon(@Query("userId") String userId);

}

