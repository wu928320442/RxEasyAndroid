package com.wjj.easy.rxeasyandroidHelper.common.rx;

import com.wjj.easy.rxeasyandroidHelper.model.http.base.BaseResponseInfo;
import com.wjj.easy.rxeasyandroidHelper.model.http.base.BaseStatus;
import com.wjj.easy.rxeasyandroidHelper.model.http.exception.ApiException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Rx工具类
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponseInfo<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseResponseInfo<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseResponseInfo<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseResponseInfo<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponseInfo<T> tMyHttpResponse) {
                        if(tMyHttpResponse.getCode() == BaseStatus.RESPONSE_CODE_SUCCESS) {
                            return createData(tMyHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(tMyHttpResponse.getMessage(), tMyHttpResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
