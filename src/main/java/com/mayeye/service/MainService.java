package com.mayeye.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayeye.dao.BoardDAO;
import com.mayeye.dto.ContentDTO;

@Service
public class MainService implements MainServiceImpl{
	
	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public List<ContentDTO> getMainList(int board_info_idx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
}
