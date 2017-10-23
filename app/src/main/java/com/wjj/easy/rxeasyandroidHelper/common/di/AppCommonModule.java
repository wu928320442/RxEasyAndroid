package com.wjj.easy.easyandroidHelper.common.di;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wjj.easy.easyandroid.http.Http;
import com.wjj.easy.easyandroid.mvp.di.modules.AppModule;
import com.wjj.easy.easyandroidHelper.common.net.ApiService;
import com.wjj.easy.easyandroidHelper.common.net.CookieInterceptor;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module
 *
 * @author wujiajun
 */
@Module
public class AppCommonModule extends AppModule {

    private Http mHttp;

    public AppCommonModule(Context context) {
        super(context);
        initHttp();
        initUtils();
    }

    /**
     * Http初始化
     */
    private void initHttp() {
        //cookie cache & persistor
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(provideContext()));
        mHttp = new Http.HttpBuilder()
                .setBaseUrl(ApiService.HOST)
                .setCookieJar(cookieJar)
                .setTimeout(15)
                .addInterceptor(new CookieInterceptor())
                .build();
    }

    /**
     * Utils库初始化
     */
    private void initUtils() {
        Utils.init(provideContext());
    }

    @Provides
    ApiService provideApiService() {
        return mHttp.getRetrofit().create(ApiService.class);
    }

}
