package com.shasly.common.bean;
/**
 * 商品
 */

import java.util.Date;

public class Goods {
	private Integer gid;
	private Integer tid;
	private String name;
	private Date put_date;
	private String picture;
	private double price;
	private String intro;

	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public Goods(Integer gid, Integer tid, String name, Date put_date, String picture, double price, String intro) {
		this.gid = gid;
		this.tid = tid;
		this.name = name;
		this.put_date = put_date;
		this.picture = picture;
		this.price = price;
		this.intro = intro;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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

	@Override
	public String toString() {
		return "Goods{" +
				"gid=" + gid +
				", tid=" + tid +
				", name='" + name + '\'' +
				", put_date=" + put_date +
				", picture='" + picture + '\'' +
				", price=" + price +
				", intro='" + intro + '\'' +
				'}';
	}
}
