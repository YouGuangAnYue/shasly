package com.shasly.common.bean;

/**
 * 用户地址
 */
public class Address {
	private Integer aid;
	private Integer uid;
	private String name;
	private String path;
	private String phone;
	private Integer def;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "Address{" +
				"aid=" + aid +
				", uid=" + uid +
				", name='" + name + '\'' +
				", path='" + path + '\'' +
				", phone='" + phone + '\'' +
				", def=" + def +
				'}';
	}
}
