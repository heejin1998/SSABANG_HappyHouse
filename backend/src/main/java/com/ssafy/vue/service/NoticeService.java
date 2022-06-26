package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Notice;

public interface NoticeService {

	List<Notice> retrieveNotice();

	Notice detailNotice(String nid);

	boolean writeNotice(Notice notice);

	boolean updateNotice(String nid, Notice notice);

	boolean deleteNotice(String nid);
	
	void updateHit(String nid);

}
