package com.ssafy.vue.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Favor;
import com.ssafy.vue.dto.User;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public boolean isUser(String kid) {
		
		return memberMapper.isUser(kid)==1;
	}

	@Override
	public boolean checkUsername(String username) {
		System.out.println("checkUserName 반환: "+ memberMapper.checkUsername(username) );
		return memberMapper.checkUsername(username)==1;
	}

	@Override
	public boolean registerUser(User user) {
		return memberMapper.registerUser(user);
	}
	
	@Override
	public List<User> retrieveUser() {
		return memberMapper.retrieveUser();
	}

	@Override
	public User detailUser(String kid) {
		return memberMapper.detailUser(kid);
	}

	@Override
	public boolean deleteUser(String kid) {
		return memberMapper.deleteUser(kid);
	}

	@Override
	public boolean updateUser(@Param("kid") String kid,@Param("user") User user) {
		return memberMapper.updateUser(kid, user);
	}

	@Override
	public List<Favor> userFavor(String kid) {
		return memberMapper.userFavor(kid);
	}

	@Override
	public boolean insertFavor(@Param("kid") String kid, @Param("aptCode") String aptCode) {
		return memberMapper.insertFavor(kid, aptCode);
	}

	@Override
	public boolean deleteFavor(@Param("kid") String kid, @Param("aptCode") String aptCode) {
		return memberMapper.deleteFavor(kid, aptCode);
	}

	

}
