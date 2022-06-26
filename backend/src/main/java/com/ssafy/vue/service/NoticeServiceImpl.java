package com.ssafy.vue.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Notice;
import com.ssafy.vue.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> retrieveNotice() {
		return noticeMapper.retrieveNotice();
	}

	@Override
	public Notice detailNotice(String nid) {
		updateHit(nid);
		return noticeMapper.detailNotice(nid);
	}

	@Override
	public boolean writeNotice(Notice notice) {
		return noticeMapper.writeNotice(notice)==1;
	}

	@Override
	public boolean updateNotice(@Param("nid") String nid,@Param("notice")  Notice notice) {
		return noticeMapper.updateNotice(nid, notice);
	}

	@Override
	public boolean deleteNotice(String nid) {
		return noticeMapper.deleteNotice(nid);
	}

	@Override
	public void updateHit(String nid) {
		noticeMapper.updateHit(nid);
		
	}
	

}
