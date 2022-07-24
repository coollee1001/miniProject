package com.mayeye.service;

import com.mayeye.dto.UserDTO;

public interface UserServiceImpl {
	public boolean checkuserIdExist(String userid);
	public void addUserInfo(UserDTO userDTO);
	public void getLoginUserInfo(UserDTO tempLoginUserDTO);
	public void getModifyUserInfo(UserDTO modifyUserDTO);
	public void modifyUserInfo(UserDTO modifyUserDTO);
}
