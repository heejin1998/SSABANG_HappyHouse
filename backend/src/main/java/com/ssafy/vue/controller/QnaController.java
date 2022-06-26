package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Qna;
import com.ssafy.vue.dto.QnaReply;
import com.ssafy.vue.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	QnaService service;
	
	@GetMapping
	public ResponseEntity<?> retrieveQna() { // Qna 게시글 목록
		logger.debug("retrieveQna - 호출");
		return new ResponseEntity<List<Qna>>(service.selectAll(), HttpStatus.OK);
		
	}
	
	@GetMapping("{qid}")
	public ResponseEntity<?> detailQna(@PathVariable String qid){ // Qna 게시글 상세 조회
		logger.debug("detailQna - 호출");
		return new ResponseEntity<Qna>(service.selectOne(qid), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> writeQna(@RequestBody Qna qna) { // Qna 게시글 작성
		logger.debug("writeQna - 호출");
		if (service.insert(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{qid}")
	public ResponseEntity<?> deleteQna(@PathVariable String qid) { // Qna 게시글 삭제
		logger.debug("deleteQna - 호출");
		if (service.delete(qid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{qid}")
	public ResponseEntity<?> updateQna(@PathVariable String qid, @RequestBody Qna qna) { // Qna 게시글 수정
logger.debug("updateQna - 호출");
		
		if (service.update(qid, qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/reply/{qid}")
	public ResponseEntity<?> listReply(@PathVariable String qid) { // Qna 게시글 목록
		logger.debug("listReply - 호출");
		return new ResponseEntity<List<Qna>>(service.selectAllReply(qid), HttpStatus.OK);
		
	}
	
	@PostMapping("/reply/{qid}")
	public ResponseEntity<?> writeReply(@PathVariable String qid, @RequestBody QnaReply reply) { // Qna 게시글 작성
		logger.debug("writeQna - 호출");
		if (service.insertReply(qid, reply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	

}
