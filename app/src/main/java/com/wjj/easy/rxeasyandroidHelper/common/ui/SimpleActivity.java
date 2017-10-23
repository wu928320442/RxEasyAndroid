package com.wjj.easy.rxeasyandroidHelper.common.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.wjj.easy.easyandroid.ui.EasyActivity;
import com.wjj.easy.rxeasyandroidHelper.R;
import com.wjj.easy.rxeasyandroidHelper.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity业务基类简单实现（不包含Presenter）
 *
 * @author wujiajun
 */

public abstract class SimpleActivity extends EasyActivity {

    protected DialogLoading loading;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
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
    }

    protected abstract void initEventAndData();

    //common
    public void toast(String msg) {
        ToastUtils.showShortToast(msg);
    }

    public void showLoading() {
        loading.show();
    }

    public void hiddenLoading() {
        loading.hide();
    }

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
