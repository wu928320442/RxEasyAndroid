package com.wjj.easy.rxeasyandroidHelper.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.jaeger.library.StatusBarUtil;
import com.wjj.easy.rxeasyandroidHelper.R;

/**
 * Created by wujiajun on 2017/11/1.
 */

public class StatusBarUtils {

    public static void commonTheme(Activity activity) {
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, 20, null);
    }

    public static void adjustStatusBarHeight(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View statusBarView = activity.findViewById(R.id.status_bar_view);
            if (statusBarView != null) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) statusBarView.getLayoutParams();
                params.height = BarUtils.getStatusBarHeight(activity);
                statusBarView.setLayoutParams(params);
            }
        }
    }
}
