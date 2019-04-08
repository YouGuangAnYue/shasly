package com.shasly.goods.vo;

import com.shasly.common.bean.GoodsType;

public class GoodsDetails {
    //商品id
    private Integer gid;
    //商品名称
    private String name;
    //商品图片
    private String picture;
    //商品价格
    private double price;
    //商品介绍
    private String intro;
    //一级分类id
    private GoodsType type_1;
    //二级分类id
    private GoodsType type_2;
    //三级分类id
    private GoodsType type_3;
    //商品星级
    private long star;
    //商品评分
    private double point;
    //销量
    private long sales_volume;
    //总收藏量
    private long total_collection;
    //总评论数
    private long total_comment;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public long getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(long sales_volume) {
        this.sales_volume = sales_volume;
    }

    public long getTotal_collection() {
        return total_collection;
    }

    public void setTotal_collection(long total_collection) {
        this.total_collection = total_collection;
    }

    public long getTotal_comment() {
        return total_comment;
    }

    public void setTotal_comment(long total_comment) {
        this.total_comment = total_comment;
    }

    public GoodsType getType_1() {
        return type_1;
    }

    public void setType_1(GoodsType type_1) {
        this.type_1 = type_1;
    }

    public GoodsType getType_2() {
        return type_2;
    }

    public void setType_2(GoodsType type_2) {
        this.type_2 = type_2;
    }

    public GoodsType getType_3() {
        return type_3;
    }

    public void setType_3(GoodsType type_3) {
        this.type_3 = type_3;
    }
}
