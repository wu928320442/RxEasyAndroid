package com.wjj.easy.easyandroidHelper.module;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wjj.easy.easyandroidHelper.R;
import com.wjj.easy.easyandroidHelper.model.ProductItemInfo;

/**
 * Created by wujiajun on 2017/10/18.
 */

public class ProductAdapter extends BaseQuickAdapter<ProductItemInfo, BaseViewHolder> {

    public ProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductItemInfo item) {
        ImageView img = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(item.getImg_url()).into(img);
        helper.setText(R.id.tv_product_title, item.getName());
        helper.setText(R.id.tv_product_price, "¥" + item.getAmount());
        helper.setText(R.id.tv_product_vip_price, "¥" + item.getVip_amount());
    }
}
