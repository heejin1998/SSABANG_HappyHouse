package com.ssafy.vue.dto;

public class QnaReply {
	String rid;
	String qid;
	String username;
	String content;
	String regtime;
	public QnaReply(String rid, String qid, String username, String content, String regtime) {
		this.rid = rid;
		this.qid = qid;
		this.username = username;
		this.content = content;
		this.regtime = regtime;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	
	
	

}
