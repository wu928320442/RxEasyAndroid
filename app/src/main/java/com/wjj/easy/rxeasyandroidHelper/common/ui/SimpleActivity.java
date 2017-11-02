package com.wjj.easy.rxeasyandroidHelper.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.wjj.easy.easyandroid.mvp.di.modules.ActivityModule;
import com.wjj.easy.easyandroid.ui.EasyActivity;
import com.wjj.easy.rxeasyandroidHelper.AppApplication;
import com.wjj.easy.rxeasyandroidHelper.common.di.ActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.di.DaggerActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.mvp.BaseView;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.DiActivitySupport;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.StatusBarSupport;
import com.wjj.easy.rxeasyandroidHelper.utils.StatusBarUtils;
import com.wjj.easy.rxeasyandroidHelper.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity业务基类简单实现（不包含Presenter）
 *
 * @author wujiajun
 */

public abstract class SimpleActivity extends EasyActivity implements BaseView, DiActivitySupport, StatusBarSupport {

    protected DialogLoading loading;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initInject();
        initEventAndData();
    }

    protected void initView() {
        unbinder = ButterKnife.bind(this);
        loading = new DialogLoading(this);
        StatusBarUtils.adjustStatusBarHeight(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //di
    @Override
    public ActivityCommonComponent getActivityComponent() {
        return DaggerActivityCommonComponent.builder()
                .appCommonComponent(((AppApplication) getApplication()).getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void initInject() {

    }

    //baseview
    @Override
    public void toast(String msg) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hiddenLoading() {
        loading.hide();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    //statusbar
    public void commonTheme() {
        StatusBarUtils.commonTheme(this);
    }

    //custom
    protected abstract void initEventAndData();

}
