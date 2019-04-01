package com.shasly.common.bean;

import java.io.Serializable;

/**
 * 商品类型
 */
public class GoodsType implements Serializable{
	
	private int tid ;
	private String name ;
	private int level ;
	private int pid ;
	
	public GoodsType() {
		// TODO Auto-generated constructor stub
	}

	public GoodsType(int tid, String name, int level, int pid) {
		this.tid = tid;
		this.name = name;
		this.level = level;
		this.pid = pid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "GoodsType{" +
				"tid=" + tid +
				", name='" + name + '\'' +
				", level=" + level +
				", pid=" + pid +
				'}';
	}
}
