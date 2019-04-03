package com.shasly.common.bean;
/**
 * 商品
 */

import java.util.Date;

public class Goods {
	private Integer gid;
	private String name;
	private Date put_date;
	private String picture;
	private double price;
	private String intro;
	private Integer tid_1 ;
	private Integer tid_2 ;
	private Integer tid_3 ;

	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public Goods(Integer gid, String name, Date put_date, String picture, double price, String intro, Integer tid_1, Integer tid_2, Integer tid_3) {
		this.gid = gid;
		this.name = name;
		this.put_date = put_date;
		this.picture = picture;
		this.price = price;
		this.intro = intro;
		this.tid_1 = tid_1;
		this.tid_2 = tid_2;
		this.tid_3 = tid_3;
	}

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

	public Date getPut_date() {
		return put_date;
	}

	public void setPut_date(Date put_date) {
		this.put_date = put_date;
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

	public Integer getTid_1() {
		return tid_1;
	}

	public void setTid_1(Integer tid_1) {
		this.tid_1 = tid_1;
	}

	public Integer getTid_2() {
		return tid_2;
	}

	public void setTid_2(Integer tid_2) {
		this.tid_2 = tid_2;
	}

	public Integer getTid_3() {
		return tid_3;
	}

	public void setTid_3(Integer tid_3) {
		this.tid_3 = tid_3;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"gid=" + gid +
				", name='" + name + '\'' +
				", put_date=" + put_date +
				", picture='" + picture + '\'' +
				", price=" + price +
				", intro='" + intro + '\'' +
				", tid_1=" + tid_1 +
				", tid_2=" + tid_2 +
				", tid_3=" + tid_3 +
				'}';
	}
}
