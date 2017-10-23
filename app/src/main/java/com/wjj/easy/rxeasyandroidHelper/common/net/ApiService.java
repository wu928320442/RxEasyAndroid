package com.wjj.easy.rxeasyandroidHelper.common.net;

import com.wjj.easy.rxeasyandroidHelper.model.ProductItemInfo;
import com.wjj.easy.rxeasyandroidHelper.model.http.base.BaseResponseInfo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wujiajun on 17/4/5.
 */

public interface ApiService {

    String HOST = "http://www.ibooyo.com/";

    @GET("api/index/goods.json")
    Flowable<BaseResponseInfo<List<ProductItemInfo>>> getProductList(@Query("page") int page,
                                                                     @Query("rows") int rows,
                                                                     @Query("type") String type);
}
