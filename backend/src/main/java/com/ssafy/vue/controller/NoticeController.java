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

import com.ssafy.vue.dto.Notice;
import com.ssafy.vue.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService service;
	
	@GetMapping
	public ResponseEntity<List<Notice>> retrieveNotice() throws Exception {
		logger.debug("retrieveNotice - 호출");
		return new ResponseEntity<List<Notice>>(service.retrieveNotice(), HttpStatus.OK);
	}
	
	@GetMapping("{nid}")
	public ResponseEntity<Notice> detailBoard(@PathVariable String nid) {
		logger.debug("detailNotice - 호출");
		return new ResponseEntity<Notice>(service.detailNotice(nid), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> writeNotice(@RequestBody Notice notice) {
		logger.debug("writeNotice - 호출");
		if (service.writeNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{nid}")
	public ResponseEntity<String> updateBoard(@PathVariable String nid, @RequestBody Notice notice) {
		logger.debug("updateNotice - 호출");
		
		if (service.updateNotice(nid, notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{nid}")
	public ResponseEntity<String> deleteBoard(@PathVariable String nid) {
		logger.debug("deleteNotice - 호출");
		if (service.deleteNotice(nid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
