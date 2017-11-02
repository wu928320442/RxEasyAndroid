package com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces;

import com.wjj.easy.easyandroid.mvp.di.modules.ActivityModule;
import com.wjj.easy.rxeasyandroidHelper.common.di.ActivityCommonComponent;

/**
 * Created by wujiajun on 2017/11/1.
 */

public interface DiActivitySupport {
    ActivityCommonComponent getActivityComponent();

    ActivityModule getActivityModule();

    void initInject();
}
