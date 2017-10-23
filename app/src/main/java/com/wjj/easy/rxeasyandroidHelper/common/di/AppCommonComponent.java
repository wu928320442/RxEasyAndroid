package com.wjj.easy.easyandroidHelper.common.di;

import com.wjj.easy.easyandroid.mvp.di.components.AppComponent;
import com.wjj.easy.easyandroid.mvp.di.scopes.ApplicationScope;
import com.wjj.easy.easyandroidHelper.common.net.ApiService;

import dagger.Component;

/**
 * Application注入器
 *
 * @author wujiajun
 */
@ApplicationScope
@Component(modules = AppCommonModule.class)
public interface AppCommonComponent extends AppComponent {
    ApiService getApiService();
}
