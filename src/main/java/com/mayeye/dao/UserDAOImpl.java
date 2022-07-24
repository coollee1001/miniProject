package com.mayeye.dao;

import com.mayeye.dto.UserDTO;

public interface UserDAOImpl {
	
	public String checkUserIdExist(String user_id);
	public void addUserInfo(UserDTO userDTO);
	public UserDTO getLoginUserInfo(UserDTO tempLoginUserDTO);
	public UserDTO getModifyUserInfo(int user_idx);
	public void modifyUserInfo(UserDTO modifyUserDTO);
}
