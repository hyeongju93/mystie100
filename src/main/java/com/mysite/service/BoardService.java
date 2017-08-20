package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.PageVo;
import com.mysite.vo.boardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardao;

	
	public List<boardVo> getlist(int currNo,PageVo pageVo) {		
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return boardao.getlist(endNum,startNum);
	}
	
	public PageVo getPage(int currNo) {
		int totalCount=boardao.gettotalcount();
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}
	
	public boardVo getboard(boardVo boardvo,int flag) {
		boardVo vo=boardao.getboard(boardvo);
		if(flag==1) {
			boardao.count(boardvo);
		}
		vo.setContent(vo.getContent().replace("\r\n", "<br/>"));
		return vo;
	}
	
	public boardVo getboardinfo(boardVo boardvo) {
		boardVo vo=boardao.getboard(boardvo);
		return vo;
	}
	
	public int insert(boardVo boardvo) {
		return boardao.insert(boardvo);
	}
	
	public int update(boardVo boardvo) {
		return boardao.update(boardvo);
	}
	
	public int delete(int num) {
		return boardao.delete(num);
	}
	
	public int count(boardVo boardvo) {
		return boardao.count(boardvo);
	}	
	
	public List<boardVo> search(int currNo,String name) {
		PageVo pageVo=new PageVo();
		int totalCount=boardao.searchcount(name);
		pageVo.setting(currNo, totalCount);
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return boardao.search(name,startNum,endNum);
	}
	
	public PageVo searchPage(int currNo,String name) {
		int totalCount=boardao.searchcount(name);
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}

}
