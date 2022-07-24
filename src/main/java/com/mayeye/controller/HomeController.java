package com.mayeye.controller;

//import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//import com.mayeye.dto.UserDTO;

@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Resource(name = "loginUserDTO")
	//@Lazy
	//private UserDTO loginUserDTO;
	
	@GetMapping(value = "/")
	public String home() {
		logger.info("HOME");
		//System.out.println(loginUserDTO);
		return "redirect:/main";
	}
	
}
