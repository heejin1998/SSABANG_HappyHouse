package com.ssafy.vue.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.vue.dto.Qna;
import com.ssafy.vue.dto.QnaReply;

public interface QnaService {

	ArrayList<Qna> selectAll();

	Qna selectOne(String qid);

	boolean insert(Qna q);

	boolean delete(String qid);

	boolean update(String qid, Qna q);

	void updateHit(String qid);

	List<Qna> selectAllReply(String qid);

	boolean insertReply(String qid, QnaReply reply);
	

}
