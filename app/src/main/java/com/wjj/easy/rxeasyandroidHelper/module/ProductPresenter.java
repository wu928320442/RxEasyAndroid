package com.wjj.easy.easyandroidHelper.module;

import com.blankj.utilcode.util.LogUtils;
import com.wjj.easy.easyandroidHelper.common.mvp.BasePresenter;
import com.wjj.easy.easyandroidHelper.common.net.ApiService;
import com.wjj.easy.easyandroidHelper.common.rx.CommonSubscriber;
import com.wjj.easy.easyandroidHelper.common.rx.RxUtil;
import com.wjj.easy.easyandroidHelper.model.ProductItemInfo;
import com.wjj.easy.easyandroidHelper.model.http.base.BaseResponseInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by wujiajun on 2017/10/18.
 */

public class ProductPresenter extends BasePresenter<ProductContract.View> implements ProductContract.Presenter {

    @Inject
    ApiService apiService;

    @Inject
    public ProductPresenter() {
    }

    @Override
    public void getData(int limit, int size) {
        addSubscribe(apiService.getProductList(limit, size, "tj")
                .compose(RxUtil.<BaseResponseInfo<List<ProductItemInfo>>>rxSchedulerHelper())
                .compose(RxUtil.<List<ProductItemInfo>>handleResult())
                .doOnNext(new Consumer<List<ProductItemInfo>>() {
                    @Override
                    public void accept(List<ProductItemInfo> productItemInfos) throws Exception {
                        for(ProductItemInfo o:productItemInfos){
                            LogUtils.d(o.getName());
                        }
                    }
                })
//                .doOnNext((productItemInfos)->{
//                    for(ProductItemInfo o:productItemInfos){
//                        LogUtils.d(o.getName());
//                    }
//                })
                .subscribeWith(new CommonSubscriber<List<ProductItemInfo>>(getView()) {
                    @Override
                    public void onNext(List<ProductItemInfo> o) {
                        getView().callback(true, o);
                    }
                }));
    }
}
