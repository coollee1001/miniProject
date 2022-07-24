package com.mayeye.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayeye.dto.UserDTO;
import com.mayeye.service.UserService;
import com.mayeye.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserDTO") UserDTO tempLoginUserDTO,
						@RequestParam(value="fail", defaultValue = "false") boolean fail,
						Model model) {
		model.addAttribute("fail", fail);
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserDTO") UserDTO tempLoginUserDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "user/login";
		}
		
		userService.getLoginUserInfo(tempLoginUserDTO);
		if(loginUserDTO.isUserLogin() == true) {
			return "user/login_success";
		}else {
			return "user/login_fail";
		}
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserDTO") UserDTO joinUserDTO) {
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserDTO") UserDTO joinUserDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(joinUserDTO);
		
		return "user/join_success";
	}
		
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO) {
		userService.getModifyUserInfo(modifyUserDTO);
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserDTO") UserDTO modifyUserDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/modify";
		}
		
		userService.modifyUserInfo(modifyUserDTO);
		
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		loginUserDTO.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
	 	binder.addValidators(validator1);
		
	}
}
