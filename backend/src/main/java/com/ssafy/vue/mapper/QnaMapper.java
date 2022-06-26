package com.ssafy.vue.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.Qna;
import com.ssafy.vue.dto.QnaReply;

@Mapper
public interface QnaMapper {
	
	ArrayList<Qna> selectAll();

	Qna selectOne(String qid);

	int insert(Qna q);

	boolean delete(String qid);

	boolean update(@Param("qid") String qid, @Param("qna") Qna q);
	
	void updateHit(String qid);

	List<Qna> selectAllReply(String qid);

	boolean insertReply(@Param("qid") String qid,@Param("reply") QnaReply reply);


}
