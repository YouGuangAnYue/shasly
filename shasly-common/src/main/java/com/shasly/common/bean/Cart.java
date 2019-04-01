package com.shasly.common.bean;
/**
 * CREATE TABLE `tb_cart` ( `id` INT(11) NOT NULL, `pid` INT(11) NOT NULL
 * DEFAULT '0', `Num` INT(11) DEFAULT NULL, `money` DECIMAL(11,2) DEFAULT NULL,
 * PRIMARY KEY (`id`,`pid`), KEY `fk_cart_pid` (`pid`), CONSTRAINT `fk_cart_id`
 * FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`), CONSTRAINT `fk_cart_pid`
 * FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`) ) ENGINE=INNODB DEFAULT
 * CHARSET=utf8;
 * 
 * @author Administrator
 *
 */
public class Cart {

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
