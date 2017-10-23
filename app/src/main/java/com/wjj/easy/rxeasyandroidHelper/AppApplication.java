package com.wjj.easy.rxeasyandroidHelper;

import android.app.Application;

import com.wjj.easy.rxeasyandroidHelper.common.di.AppCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.di.AppCommonModule;
import com.wjj.easy.rxeasyandroidHelper.common.di.DaggerAppCommonComponent;

/**
 * Created by wujiajun on 17/4/6.
 */

public class AppApplication extends Application {

    AppCommonComponent aComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        aComponent = DaggerAppCommonComponent.builder().appCommonModule(new AppCommonModule(this)).build();
    }

    public AppCommonComponent getAppComponent() {
        return aComponent;
    }
}
