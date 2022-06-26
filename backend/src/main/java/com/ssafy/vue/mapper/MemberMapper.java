package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Favor;
import com.ssafy.vue.dto.User;

@Mapper
public interface MemberMapper {

	int isUser(String kid);

	int checkUsername(String username);

	boolean registerUser(User user);

	User detailUser(String kid);

	boolean deleteUser(String kid);

	boolean updateUser(String kid, User user);

	List<User> retrieveUser();

	List<Favor> userFavor(String kid);

	boolean insertFavor(String kid, String aptCode);

	boolean deleteFavor(String kid, String aptCode);

}
