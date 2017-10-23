package com.wjj.easy.rxeasyandroidHelper.common.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.wjj.easy.easyandroid.mvp.EasyBasePresenter;
import com.wjj.easy.easyandroid.mvp.di.modules.ActivityModule;
import com.wjj.easy.easyandroid.ui.EasyActivity;
import com.wjj.easy.rxeasyandroidHelper.AppApplication;
import com.wjj.easy.rxeasyandroidHelper.R;
import com.wjj.easy.rxeasyandroidHelper.common.di.ActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.di.DaggerActivityCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.mvp.BaseView;
import com.wjj.easy.rxeasyandroidHelper.widget.dialog.DialogLoading;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity业务基类
 * Created by wujiajun on 17/4/10.
 */

public abstract class BaseActivity<P extends EasyBasePresenter> extends EasyActivity implements BaseView {

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
        adjustStatusBarHeight();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPresenter.detachView();
    }

    protected ActivityCommonComponent getActivityComponent() {
        return DaggerActivityCommonComponent.builder()
                .appCommonComponent(((AppApplication) getApplication()).getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract void initInject();

    protected abstract void initEventAndData();

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
    protected void commonTheme() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 20, null);
    }

    protected void adjustStatusBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBarView = findViewById(R.id.status_bar_view);
            if (statusBarView != null) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) statusBarView.getLayoutParams();
                params.height = BarUtils.getStatusBarHeight(this);
                statusBarView.setLayoutParams(params);
            }
        }
    }
}
