package com.mayeye.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mayeye.dto.BoardInfoDTO;
import com.mayeye.dto.UserDTO;
import com.mayeye.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoDTO> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserDTO", loginUserDTO);
		return true;
	}
	
}
