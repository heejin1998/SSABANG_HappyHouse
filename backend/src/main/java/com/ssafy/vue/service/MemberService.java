package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Favor;
import com.ssafy.vue.dto.User;

public interface MemberService {

	boolean isUser(String kid);

	boolean checkUsername(String username);

	boolean registerUser(User user);

	User detailUser(String kid);

	boolean deleteUser(String kid);

	boolean updateUser(String kid, User user);

	List<User> retrieveUser();

	List<Favor> userFavor(String kid);

	boolean insertFavor(String kid, String aptCode);

	boolean deleteFavor(String kid, String aptCode);

}
