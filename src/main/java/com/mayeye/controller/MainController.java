package com.mayeye.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mayeye.dto.BoardInfoDTO;
import com.mayeye.dto.ContentDTO;
import com.mayeye.service.MainService;
import com.mayeye.service.TopMenuService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@GetMapping("/main")
	public String main(Model model) {
		ArrayList<List<ContentDTO>> list = new ArrayList<List<ContentDTO>>();
		
		for(int i = 1 ; i <= 4 ; i++) {
			List<ContentDTO> list1 = mainService.getMainList(i);
			list.add(list1);
		}
		
		model.addAttribute("list", list);
		
		List<BoardInfoDTO> board_list = topMenuService.getTopMenuList();
		model.addAttribute("board_list", board_list);
		return "main";
	}
	
}
