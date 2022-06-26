package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Notice;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {

	List<Notice> retrieveNotice();

	Notice detailNotice(String nid);

	int writeNotice(Notice notice);

	boolean updateNotice(String nid, Notice notice);

	boolean deleteNotice(String nid);

	void updateHit(String nid);

}
