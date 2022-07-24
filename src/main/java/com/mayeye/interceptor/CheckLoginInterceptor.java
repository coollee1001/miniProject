package com.mayeye.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mayeye.dto.UserDTO;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	@Resource(name="loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(loginUserDTO.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login"); 
			return false;
		}
		return true;
	}
}
