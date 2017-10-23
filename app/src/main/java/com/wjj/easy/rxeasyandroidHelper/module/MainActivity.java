package com.wjj.easy.easyandroidHelper.module;

import android.content.Intent;
import android.view.View;

import com.wjj.easy.easyandroidHelper.R;
import com.wjj.easy.easyandroidHelper.common.ui.SimpleActivity;
import com.wjj.easy.easyandroidHelper.module.demo1.Demo1Activity;
import com.wjj.easy.easyandroidHelper.module.demo2.Demo2Activity;
import com.wjj.easy.easyandroidHelper.module.demo3.Demo3Activity;
import com.wjj.easy.easyandroidHelper.module.demo4.Demo4Activity;

import butterknife.OnClick;

/**
 * 主页
 *
 * @author wujiajun
 */
public class MainActivity extends SimpleActivity {

    @Override
    protected void initEventAndData() {
        commonTheme();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, Demo2Activity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, Demo3Activity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(MainActivity.this, Demo4Activity.class));
                break;
        }
    }
}
