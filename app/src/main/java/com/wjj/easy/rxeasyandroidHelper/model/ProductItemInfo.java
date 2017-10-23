package com.wjj.easy.easyandroidHelper.model;

public class ProductItemInfo{

    private String goods_id;//商品ID
    private String img_url;//头图
    private String name;//商品名称
    private String sub_name;//商品副标题
    private double amount;//价格(单位:分)／get方法已转成元
    private double vip_amount;//会员价格(单位:分)／get方法已转成元
    private boolean allow_rece;//是否允许购买(true-是,false-否)
    private String rece_desc;//购买描述
    private boolean vip;//是否是会员(true-是,false-否)
    private int vip_goods;//是否VIP商品  0-非会员商品，1-会员商品，2-会员专享

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVip_amount() {
        return vip_amount;
    }

    public void setVip_amount(double vip_amount) {
        this.vip_amount = vip_amount;
    }

    public boolean isAllow_rece() {
        return allow_rece;
    }

    public void setAllow_rece(boolean allow_rece) {
        this.allow_rece = allow_rece;
    }

    public String getRece_desc() {
        return rece_desc;
    }

    public void setRece_desc(String rece_desc) {
        this.rece_desc = rece_desc;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getVip_goods() {
        return vip_goods;
    }

    public void setVip_goods(int vip_goods) {
        this.vip_goods = vip_goods;
    }
}
