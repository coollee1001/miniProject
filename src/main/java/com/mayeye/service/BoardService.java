package com.mayeye.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayeye.dao.BoardDAO;
import com.mayeye.dto.ContentDTO;
import com.mayeye.dto.PageDTO;
import com.mayeye.dto.UserDTO;

@Service
public class BoardService implements BoardServiceImpl{
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private BoardDAO boardDao;
	
	@Resource(name = "loginUserDTO")
	@Lazy
	private UserDTO loginUserDTO;
	
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
			System.out.println(path_upload + "/" + file_name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}

	@Override
	public void addContentInfo(ContentDTO writeContentDTO) {
		// TODO Auto-generated method stub
		MultipartFile upload_file = writeContentDTO.getUpload_file();
		
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			writeContentDTO.setContent_file(file_name);
		}
		
		writeContentDTO.setContent_writer_idx(loginUserDTO.getUser_idx());
		
		boardDao.addContentInfo(writeContentDTO);
	}

	@Override
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}

	@Override
	public List<ContentDTO> getContentList(int board_info_idx, int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		
		return boardDao.getContentList(board_info_idx, rowBounds);
	}

	@Override
	public ContentDTO getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}

	@Override
	public void modifyContentInfo(ContentDTO modifyContentDTO) {

		MultipartFile upload_file = modifyContentDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			modifyContentDTO.setContent_file(file_name);
		}
		
		boardDao.modifyContentInfo(modifyContentDTO);
		
	}

	@Override
	public void deleteContentInfo(int content_idx) {
		boardDao.deleteContentInfo(content_idx);
		
	}

	@Override
	public PageDTO getContentCnt(int content_board_idx, int currentPage) {
		int content_cnt = boardDao.getContentCnt(content_board_idx);
		
		PageDTO pageDTO = new PageDTO(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageDTO;
	}
	
}
