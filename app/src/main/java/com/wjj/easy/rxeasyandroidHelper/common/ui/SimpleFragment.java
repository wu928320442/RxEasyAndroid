package com.wjj.easy.rxeasyandroidHelper.common.ui;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.wjj.easy.easyandroid.mvp.di.modules.FragmentModule;
import com.wjj.easy.easyandroid.ui.EasyFragment;
import com.wjj.easy.rxeasyandroidHelper.AppApplication;
import com.wjj.easy.rxeasyandroidHelper.common.di.DaggerFragmentCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.di.FragmentCommonComponent;
import com.wjj.easy.rxeasyandroidHelper.common.mvp.BaseView;
import com.wjj.easy.rxeasyandroidHelper.common.ui.interfaces.DiFragmentSupport;
import com.wjj.easy.rxeasyandroidHelper.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment业务基类(不包含Presenter)
 *
 * @author wujiajun
 */

public abstract class SimpleFragment extends EasyFragment implements BaseView, DiFragmentSupport {

    protected DialogLoading loading;
    private Unbinder unbinder;

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        loading = new DialogLoading(getActivity());
    }

    @Override
    protected void init(View view) {
        initEventAndData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //di
    @Override
    public FragmentCommonComponent getFragmentComponent() {
        return DaggerFragmentCommonComponent.builder()
                .appCommonComponent(((AppApplication) getActivity().getApplication()).getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public FragmentModule getFragmentModule() {
        return new FragmentModule(this);
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

    //custom
    protected abstract void initEventAndData();
}
