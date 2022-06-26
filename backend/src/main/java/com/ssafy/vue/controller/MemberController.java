package com.ssafy.vue.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Favor;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.User;
import com.ssafy.vue.oauth.KakaoLogin;
import com.ssafy.vue.service.HouseMapService;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("MemberController V1")
@RestController
@CrossOrigin("*")
public class MemberController {
	
	
	KakaoLogin kakaologin = new KakaoLogin();
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemberService service;
	@Autowired
    private HouseMapService haHouseMapService;
	
	@RequestMapping(value="/klogin")
	public HashMap<String, String> klogin(@RequestParam String authorize_code){
		String access_token = kakaologin.getAccessToken(authorize_code);
		HashMap<String, String> userInfo = kakaologin.getUserInfo(access_token);
		return userInfo;
	}
	
	@ApiOperation(value = "회원인지 체크, 회원이면 소셜로그인시 추가정보 입력 없이 메인화면으로 redirect")
	@GetMapping("/klogin/check/{kid}")
	public ResponseEntity<String> isUser(@PathVariable String kid) {
		logger.debug("isUser - 호출");
		if (service.isUser(kid)) {
			System.out.println("fail 반환");
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		System.out.println("success 반환");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 입력한 username 중복인지 검사")
	@GetMapping("/klogin/duplicate/{username}")
	public ResponseEntity<String> checkUsername(@PathVariable String username) {
		logger.debug("checkUsername - 호출");
		if (service.checkUsername(username)) {
			
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 등록")
	@PostMapping("/user")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		logger.debug("registerUser - 호출");
		if (service.registerUser(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "모든 사용자 조회")
	@GetMapping("/user")
	public ResponseEntity<List<User>> retrieveUser() throws Exception {
		logger.debug("retrieveNotice - 호출");
		return new ResponseEntity<List<User>>(service.retrieveUser(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 정보 조회(마이페이지)")
	@GetMapping("/user/{kid}")
	public ResponseEntity<User> userDetail(@PathVariable String kid) {
		logger.debug("userDetail - 호출");
		logger.debug("kid: "+kid);
		return new ResponseEntity<User>(service.detailUser(kid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 관심매물 조회")
	@GetMapping("/user/{kid}/favor")
	public ResponseEntity<List<Favor>> userFavor(@PathVariable String kid) throws NumberFormatException, Exception { //일단 지금은 List<Favor> 반환하고, 거래정보 상세조회 기능 구현되면 aptCode로 정보 얻어와서 List<HouseDeal> 반환하기
		logger.debug("userFavor - 호출");
		logger.debug("kid: "+kid);
		
		return new ResponseEntity<List<Favor>>(service.userFavor(kid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 관심매물 추가")
	@PostMapping("/user/{kid}/favor/{aptCode}")
	public ResponseEntity<String> insertFavor(@PathVariable String kid, @PathVariable String aptCode) {
		logger.debug("insertFavor - 호출");
		
		if(service.insertFavor(kid, aptCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "사용자 관심매물 삭제")
	@DeleteMapping("/user/{kid}/favor/{aptCode}")
	public ResponseEntity<String> deleteFavor(@PathVariable String kid, @PathVariable String aptCode) {
		logger.debug("deleteFavor - 호출");

		if(service.deleteFavor(kid, aptCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "사용자 탈퇴")
	@DeleteMapping("/user/{kid}")
	public ResponseEntity<String> deleteUser(@PathVariable String kid) {
		logger.debug("deleteUser - 호출");
		if (service.deleteUser(kid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "사용자 정보 수정")
	@PutMapping("/user/{kid}")
	public ResponseEntity<String> updateUser(@PathVariable String kid, @RequestBody User user) {
		logger.debug("updateUser - 호출");
		if (service.updateUser(kid, user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	
}
