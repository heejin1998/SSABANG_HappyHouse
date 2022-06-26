package com.ssafy.vue.dto;

public class Qna {
	String qid;
	String username;
	String title;
	String content;
	String hit;
	String regtime;
	public Qna(String qid, String username, String title, String content, String hit, String regtime) {
		this.qid = qid;
		this.username = username;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regtime = regtime;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	

}
