package com.wjj.easy.rxeasyandroidHelper.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.wjj.easy.easyandroid.mvp.EasyBasePresenter;
import com.wjj.easy.easyandroid.mvp.di.modules.ActivityModule;
import com.wjj.easy.easyandroid.ui.EasyActivity;
import com.wjj.easy.rxeasyandroidHelper.AppApplication;
import com.wjj.easy.rxeasyandroidHelper.common.di.ActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.di.DaggerActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.mvp.BaseView;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.DiActivitySupport;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.PresenterSupport;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.StatusBarSupport;
import com.wjj.easy.rxeasyandroidHelper.utils.StatusBarUtils;
import com.wjj.easy.rxeasyandroidHelper.widget.dialog.DialogLoading;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity业务基类
 * Created by wujiajun on 17/4/10.
 */

public abstract class BaseActivity<P extends EasyBasePresenter> extends EasyActivity implements BaseView, DiActivitySupport, PresenterSupport<P>, StatusBarSupport {

    protected DialogLoading loading;
    private Unbinder unbinder;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
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
        mPresenter.detachView();
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
    public abstract void initInject();

    //presenter
    @Override
    public P getPresenter() {
        return mPresenter;
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
    @Override
    public void commonTheme() {
        StatusBarUtils.commonTheme(this);
    }

    //custom
    protected abstract void initEventAndData();

}
