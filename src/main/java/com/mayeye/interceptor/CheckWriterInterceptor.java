package com.mayeye.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mayeye.dto.ContentDTO;
import com.mayeye.dto.UserDTO;
import com.mayeye.service.BoardService;

public class CheckWriterInterceptor  implements HandlerInterceptor {

	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	@Autowired
	private BoardService boardService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		String str1 = request.getParameter("content_idx");
		int content_idx = Integer.parseInt(str1);
		
		ContentDTO currentContentDTO = boardService.getContentInfo(content_idx);
		
		if(currentContentDTO.getContent_writer_idx() != loginUserDTO.getUser_idx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		
		return true;
	}
}