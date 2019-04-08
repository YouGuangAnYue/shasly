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

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	private Integer id;
	private String oid ;
	private Integer uid;
	private BigDecimal total_price ;
	private Integer status;
	private Long create_time;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, String oid, Integer uid, BigDecimal total_price, Integer status, Long create_time) {
		this.id = id;
		this.oid = oid;
		this.uid = uid;
		this.total_price = total_price;
		this.status = status;
		this.create_time = create_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", oid='" + oid + '\'' +
				", uid=" + uid +
				", total_price=" + total_price +
				", status=" + status +
				", create_time=" + create_time +
				'}';
	}
}
