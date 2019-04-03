package com.shasly.common.bean;

public class CartDetail {

	private Integer id ;
	private Integer cid ;
	private Integer gid ;
	private Integer number ;
	private String param ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", cid=" + cid +
				", gid=" + gid +
				", number=" + number +
				", param='" + param + '\'' +
				'}';
	}
}
