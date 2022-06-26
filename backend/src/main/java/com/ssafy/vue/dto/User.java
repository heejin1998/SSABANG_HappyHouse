package com.ssafy.vue.dto;

public class User {
	String kid;
	String nickname;
	String email;
	String username;
	String address;
	String regdate;
	public User(String kid, String nickname, String email, String username, String address,
			String regdate) {
		this.kid = kid;
		this.nickname = nickname;
		this.email = email;
		this.username = username;
		this.address = address;
		this.regdate = regdate;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
