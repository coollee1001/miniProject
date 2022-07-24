package com.mayeye.service;

import java.util.List;

import com.mayeye.dto.ContentDTO;

public interface MainServiceImpl {
	public List<ContentDTO> getMainList(int board_info_idx);
}
