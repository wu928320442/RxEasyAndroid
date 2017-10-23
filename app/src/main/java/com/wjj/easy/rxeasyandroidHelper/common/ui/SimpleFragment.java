package com.wjj.easy.easyandroidHelper.common.ui;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.wjj.easy.easyandroid.ui.EasyFragment;
import com.wjj.easy.easyandroidHelper.widget.dialog.DialogLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment业务基类(不包含Presenter)
 *
 * @author wujiajun
 */

public abstract class SimpleFragment extends EasyFragment {

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

}
