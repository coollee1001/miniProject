package com.mayeye.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mayeye.dto.ContentDTO;

@Repository
public class BoardDAO implements BoardDAOImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "board.";
	
	@Override
	public void addContentInfo(ContentDTO writeContentDTO) {
		sqlSessionTemplate.insert(NAMESPACE+"addContentInfo", writeContentDTO);
	}

	@Override
	public String getBoardInfoName(int board_info_idx) {
		return sqlSessionTemplate.selectOne(NAMESPACE+"getBoardInfoName", board_info_idx);
	}

	@Override
	public List<ContentDTO> getContentList(int board_info_idx, RowBounds rowBounds) {
		return sqlSessionTemplate.selectList(NAMESPACE+"getContentList", board_info_idx, rowBounds);
	}

	@Override
	public ContentDTO getContentInfo(int content_idx) {
		return sqlSessionTemplate.selectOne(NAMESPACE+"getContentInfo", content_idx);
	}

	@Override
	public void modifyContentInfo(ContentDTO modifyContentDTO) {
		sqlSessionTemplate.selectOne(NAMESPACE+"modifyContentInfo", modifyContentDTO);
	}

	@Override
	public void deleteContentInfo(int content_idx) {
		sqlSessionTemplate.delete(NAMESPACE+"deleteContentInfo", content_idx);
	}

	@Override
	public int getContentCnt(int content_board_idx) {
		return sqlSessionTemplate.selectOne(NAMESPACE+"getContentCnt", content_board_idx);
	}
 
}
