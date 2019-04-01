package com.shasly.common.bean;

/**
 * CREATE TABLE `tb_orderdetail` ( `id` INT(11) NOT NULL AUTO_INCREMENT, `Oid`
 * VARCHAR(100) DEFAULT NULL, `pid` INT(11) DEFAULT NULL, `num` INT(11) DEFAULT
 * NULL, `Money` DECIMAL(11,2) DEFAULT NULL, PRIMARY KEY (`id`), KEY
 * `fk_order_pid` (`pid`), KEY `fk_order_id` (`Oid`), CONSTRAINT `fk_order_id`
 * FOREIGN KEY (`Oid`) REFERENCES `tb_order` (`id`), CONSTRAINT `fk_order_pid`
 * FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`) ) ENGINE=INNODB DEFAULT
 * CHARSET=utf8;
 * 
 * @author Administrator
 *
 */
public class OrderDetail {
	private int id;
	private String oid;
	private int pid;
	private int num;
	private double money;

	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(int id, String oid, int pid, int num, double money) {
		super();
		this.id = id;
		this.oid = oid;
		this.pid = pid;
		this.num = num;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", oid=" + oid + ", pid=" + pid + ", num=" + num + ", money=" + money + "]";
	}

}
