package com.mayeye.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mayeye.dto.UserDTO;


@Repository
public class UserDAO implements UserDAOImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "user.";
	
	@Override
	public String checkUserIdExist(String userid) { 
		return sqlSessionTemplate.selectOne(NAMESPACE+"checkUserIdExist", userid);
	}
	
	@Override
	public void addUserInfo(UserDTO userDTO) {
		sqlSessionTemplate.insert(NAMESPACE+"addUserInfo", userDTO);
	}
	
	@Override
	public UserDTO getLoginUserInfo(UserDTO tempLoginUserDTO) {
		return sqlSessionTemplate.selectOne(NAMESPACE+"getLoginUserInfo", tempLoginUserDTO);
	}
	
	@Override
	public UserDTO getModifyUserInfo(int user_idx) {
		return sqlSessionTemplate.selectOne(NAMESPACE+"getModifyUserInfo", user_idx);
	}
	
	public void modifyUserInfo(UserDTO modifyUserDTO) {
		sqlSessionTemplate.update(NAMESPACE+"modifyUserInfo", modifyUserDTO);
	}
}
