package com.mayeye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayeye.dao.TopMenuDAO;
import com.mayeye.dto.BoardInfoDTO;

@Service
public class TopMenuService implements TopMenuServicesImpl{
	@Autowired
	private TopMenuDAO dao;
	
	@Override
	public List<BoardInfoDTO> getTopMenuList() {
		List<BoardInfoDTO> topMenuList = dao.getTopMenuList();
		return topMenuList;
	}
}
