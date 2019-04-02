package com.shasly.common.bean;

public class User {
	private int uid;
	private String username;
	private String salt ;
	private String password;
	private String email;
	private String phone;
	private int status;
	private int role;
	private String create_time;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String email, String phone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public User(int uid, String username, String salt, String password, String email, String phone, int status, int role, String create_time) {
		this.uid = uid;
		this.username = username;
		this.salt = salt;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.role = role;
		this.create_time = create_time;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", status=" + status +
				", role=" + role +
				", create_time='" + create_time + '\'' +
				'}';
	}
}

