package com.ssafy.vue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Qna;
import com.ssafy.vue.dto.QnaReply;
import com.ssafy.vue.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaMapper mapper;


	@Override
	public ArrayList<Qna> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public Qna selectOne(String qid) {
		updateHit(qid);
		return mapper.selectOne(qid);
	}

	@Override
	public boolean insert(Qna q) {
		return mapper.insert(q)==1;
		
	}

	@Override
	public boolean delete(String qid) {
		return mapper.delete(qid);
		
	}

	@Override
	public boolean update(String qid, Qna q) {
		return mapper.update(qid,q);
		
	}

	@Override
	public void updateHit(String qid) {
		mapper.updateHit(qid);
		
	}

	@Override
	public List<Qna> selectAllReply(String qid) {
		return mapper.selectAllReply(qid);
	}

	@Override
	public boolean insertReply(String qid, QnaReply reply) {
		return mapper.insertReply(qid, reply);
	}
	
	

}
