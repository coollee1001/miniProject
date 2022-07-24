package com.mayeye.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.mayeye.dao.UserDAO;
import com.mayeye.dto.UserDTO;

@Service
public class UserService implements UserServiceImpl{
	
	@Autowired
	private UserDAO dao;
	
	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	@Override
	public boolean checkuserIdExist(String user_id) {
		String user_name = dao.checkUserIdExist(user_id);
		
		if(user_name == null) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void addUserInfo(UserDTO userDTO) {
		dao.addUserInfo(userDTO);
		
	}
	
	@Override
	public void getLoginUserInfo(UserDTO tempLoginUserDTO) {
		UserDTO tempLoginUserDTOBean = dao.getLoginUserInfo(tempLoginUserDTO);
		
		if(tempLoginUserDTOBean != null) {
			loginUserDTO.setUser_idx(tempLoginUserDTOBean.getUser_idx());
			loginUserDTO.setUser_name(tempLoginUserDTOBean.getUser_name());
			loginUserDTO.setUserLogin(true);
		}
	}
	
	@Override
	public void getModifyUserInfo(UserDTO modifyUserDTO) {
		UserDTO tempModifyUserDTO = dao.getModifyUserInfo(loginUserDTO.getUser_idx());
		
		modifyUserDTO.setUser_id(tempModifyUserDTO.getUser_id());
		modifyUserDTO.setUser_name(tempModifyUserDTO.getUser_name());
		modifyUserDTO.setUser_idx(loginUserDTO.getUser_idx());
		
	}
	
	@Override
	public void modifyUserInfo(UserDTO modifyUserDTO) {		
		modifyUserDTO.setUser_idx(loginUserDTO.getUser_idx());
		dao.modifyUserInfo(modifyUserDTO);
	}
}
