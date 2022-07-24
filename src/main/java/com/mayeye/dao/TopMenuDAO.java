package com.mayeye.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mayeye.dto.BoardInfoDTO;

@Repository
public class TopMenuDAO implements TopMenuDAOImpl{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "topmenu.";

	@Override
	public List<BoardInfoDTO> getTopMenuList() {
		List<BoardInfoDTO> topMenuList = sqlSessionTemplate.selectList(NAMESPACE+"get_topmenu_list");
		return topMenuList;
	}
}
