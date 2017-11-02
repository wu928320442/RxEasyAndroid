package com.wjj.easy.rxeasyandroidHelper.common.di;

import com.wjj.easy.easyandroid.mvp.di.components.ActivityComponent;
import com.wjj.easy.easyandroid.mvp.di.modules.ActivityModule;
import com.wjj.easy.easyandroid.mvp.di.scopes.ActivityScope;
import com.wjj.easy.rxeasyandroidHelper.module.demo1.Demo1Activity;
import com.wjj.easy.rxeasyandroidHelper.module.demo5.Demo5Activity;

import dagger.Component;

/**
 * Activity注入器
 *
 * @author wujiajun
 */
@ActivityScope
@Component(dependencies = AppCommonComponent.class, modules = {ActivityModule.class})
public interface ActivityCommonComponent extends ActivityComponent {
    void inject(Demo1Activity activity);

    void inject(Demo5Activity activity);
}
