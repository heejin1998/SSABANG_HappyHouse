package com.ssafy.vue.dto;

public class Favor {

	String fid;
	String kid;
	String aptCode;
	String aptName;
	public Favor(String fid, String kid, String aptCode, String aptName) {
		this.fid = fid;
		this.kid = kid;
		this.aptCode = aptCode;
		this.aptName = aptName;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	} 
	
	
}
