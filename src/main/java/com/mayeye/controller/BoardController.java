package com.mayeye.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayeye.dto.ContentDTO;
import com.mayeye.dto.PageDTO;
import com.mayeye.dto.UserDTO;
import com.mayeye.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;

	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			   @RequestParam(value = "page", defaultValue = "1") int page,
			   Model model) {
		model.addAttribute("board_info_idx", board_info_idx);
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);
		
		List<ContentDTO> contentList = boardService.getContentList(board_info_idx, page);
		model.addAttribute("contentList", contentList);
		
		PageDTO pageDTO = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("pageDTO", pageDTO);
		
		model.addAttribute("page", page);
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
			   @RequestParam("content_idx") int content_idx,
			   @RequestParam("page") int page,
			   Model model) {
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		ContentDTO readContentDTO = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentDTO", readContentDTO);
		
		model.addAttribute("loginUserDTO", loginUserDTO);
		model.addAttribute("page", page);
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentDTO") ContentDTO writeContentDTO,
			@RequestParam("board_info_idx") int board_info_idx) {
		writeContentDTO.setContent_board_idx(board_info_idx);
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentDTO") ContentDTO writeContentDTO, BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(writeContentDTO);
		
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
			 @RequestParam("content_idx") int content_idx,
			 @ModelAttribute("modifyContentDTO") ContentDTO modifyContentDTO,
			 @RequestParam("page") int page,
			 Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("page", page);
		
		ContentDTO tempContentDTO = boardService.getContentInfo(content_idx);
		
		modifyContentDTO.setContent_writer_name(tempContentDTO.getContent_writer_name());
		modifyContentDTO.setContent_date(tempContentDTO.getContent_date());
		modifyContentDTO.setContent_subject(tempContentDTO.getContent_subject());
		modifyContentDTO.setContent_text(tempContentDTO.getContent_text());
		modifyContentDTO.setContent_file(tempContentDTO.getContent_file());
		modifyContentDTO.setContent_writer_idx(tempContentDTO.getContent_writer_idx());
		modifyContentDTO.setContent_board_idx(board_info_idx);
		modifyContentDTO.setContent_idx(content_idx);
		
		return "board/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("writeContentDTO") ContentDTO modifyContentDTO, 
							 BindingResult result,
							 @RequestParam("page") int page,
							 Model model) {
		
		model.addAttribute("modifyContentDTO",modifyContentDTO);
		model.addAttribute("page", page);
		
		if(result.hasErrors()) {
			return "board/modify";
		}
		
		boardService.modifyContentInfo(modifyContentDTO);
		
		return "board/modify_success";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
			 @RequestParam("content_idx") int content_idx,
			 Model model) {
		boardService.deleteContentInfo(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		return "board/delete";
	}
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}
}
