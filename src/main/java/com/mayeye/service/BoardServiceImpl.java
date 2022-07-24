package com.mayeye.service;

import java.util.List;

import com.mayeye.dto.ContentDTO;
import com.mayeye.dto.PageDTO;

public interface BoardServiceImpl {
	public void addContentInfo(ContentDTO writeContentDTO);
	public String getBoardInfoName(int board_info_idx) ;
	public List<ContentDTO> getContentList(int board_info_idx, int page);
	public ContentDTO getContentInfo(int content_idx);
	public void modifyContentInfo(ContentDTO modifyContentDTO);
	public void deleteContentInfo(int content_idx);
	public PageDTO getContentCnt(int content_board_idx, int currentPage) ;
}
