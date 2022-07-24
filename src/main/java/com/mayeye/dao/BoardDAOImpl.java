package com.mayeye.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mayeye.dto.ContentDTO;

public interface BoardDAOImpl {
	public void addContentInfo(ContentDTO writeContentDTO); 
	public String getBoardInfoName(int board_info_idx);
	public List<ContentDTO> getContentList(int board_info_idx, RowBounds rowBounds);
	public ContentDTO getContentInfo(int content_idx);
	public void modifyContentInfo(ContentDTO modifyContentDTO);
	public void deleteContentInfo(int content_idx);
	public int getContentCnt(int content_board_idx);
	
}
