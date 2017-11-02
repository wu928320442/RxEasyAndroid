package com.wjj.easy.rxeasyandroidHelper.common.di;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wjj.easy.easyandroid.http.Http;
import com.wjj.easy.easyandroid.mvp.di.modules.AppModule;
import com.wjj.easy.rxeasyandroidHelper.common.net.ApiService;
import com.wjj.easy.rxeasyandroidHelper.common.net.CookieInterceptor;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

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
        initRealm(context);
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

    /**
     * init Realm
     *
     * @param context
     */
    private void initRealm(Context context) {
        Realm.init(context);
    }

    @Provides
    ApiService provideApiService() {
        return mHttp.getRetrofit().create(ApiService.class);
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
