package com.wjj.easy.rxeasyandroidHelper.module.demo5;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.wjj.easy.rxeasyandroidHelper.R;
import com.wjj.easy.rxeasyandroidHelper.common.ui.SimpleActivity;
import com.wjj.easy.rxeasyandroidHelper.model.realm.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wujiajun on 2017/11/1.
 */

public class Demo5Activity extends SimpleActivity {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.age_et)
    EditText ageEt;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private BaseQuickAdapter<User, BaseViewHolder> adapter;

    @Inject
    Realm realm;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        commonTheme();
        adapter = new BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_user, null) {
            @Override
            protected void convert(BaseViewHolder helper, User item) {
                helper.setText(R.id.show_tv, item.getName() + item.getAge());
            }
        };
        adapter.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Observable<CharSequence> nameObservable = RxTextView.textChanges(nameEt);
        Observable<CharSequence> ageObservable = RxTextView.textChanges(ageEt);
        Observable.combineLatest(nameObservable, ageObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) throws Exception {
                return TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2);
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                addBtn.setEnabled(!aBoolean);
            }
        });

        RealmResults realmResults = realm.where(User.class).findAllAsync();
        adapter.addData(realmResults);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_demo5;
    }

    @OnClick(R.id.add_btn)
    public void onViewClicked() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName(nameEt.getText().toString());
                user.setAge(Integer.valueOf(ageEt.getText().toString()));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                adapter.addData(new User(nameEt.getText().toString(), Integer.valueOf(ageEt.getText().toString())));
            }
        });
    }
}
