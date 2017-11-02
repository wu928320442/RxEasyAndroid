package com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces;

import com.wjj.easy.easyandroid.mvp.di.modules.FragmentModule;
import com.wjj.easy.rxeasyandroidHelper.common.di.FragmentCommonComponent;

/**
 * 依赖注入Fragment模块接口
 * Created by wujiajun on 2017/11/1.
 */
public interface DiFragmentSupport {
    FragmentCommonComponent getFragmentComponent();

    FragmentModule getFragmentModule();

    void initInject();
}
