package com.mayeye.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mayeye.dto.UserDTO;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO userDTO = (UserDTO)target;
		
		String dtoName = errors.getObjectName();
		
		if(dtoName.equals("joinUserDTO")|| dtoName.equals("modifyUserDTO")) {
			if(userDTO.getUser_pw().equals(userDTO.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(dtoName.equals("joinUserDTO")) {
				
				if(userDTO.isUserIdExist() == false) {
					errors.rejectValue("user_id", "DontCheckUserIdExist");
				}
			}
		}
		
		
	}

}
