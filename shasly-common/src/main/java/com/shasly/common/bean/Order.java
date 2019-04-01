package com.shasly.common.bean;
/**
 * CREATE TABLE `tb_order` (
  `id` VARCHAR(100) NOT NULL,
  `uid` INT(11) DEFAULT NULL,
  `money` DECIMAL(11,2) DEFAULT NULL,
  `status` VARCHAR(10) DEFAULT NULL,
  `time` DATETIME DEFAULT NULL,
  `aid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_uid` (`uid`),
  KEY `fk_order_aid` (`aid`),
  CONSTRAINT `fk_order_aid` FOREIGN KEY (`aid`) REFERENCES `tb_address` (`id`),
  CONSTRAINT `fk_order_uid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
 * @author Administrator
 *
 */

import java.util.Date;

public class Order {

	private String id;
	private int uid;
	private double money;
	private String status;
	private Date time;
	private int aid;

	private String address ;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String id, int uid, double money, String status, Date time, int aid, String address) {
		this.id = id;
		this.uid = uid;
		this.money = money;
		this.status = status;
		this.time = time;
		this.aid = aid;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", uid=" + uid + ", money=" + money + ", status=" + status + ", time=" + time
				+ ", aid=" + aid + ", address=" + address + "]";
	}

	
}
