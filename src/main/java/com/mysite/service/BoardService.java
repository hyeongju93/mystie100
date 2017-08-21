package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;
import com.mysite.vo.PageVo;


@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardao;

	
	public List<BoardVo> getlist(int currNo,PageVo pageVo) {		
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
	
	public BoardVo getboard(BoardVo boardvo,int flag) {
		BoardVo vo=boardao.getboard(boardvo);
		if(flag==1) {
			boardao.count(boardvo);
		}
		vo.setContent(vo.getContent().replace("\r\n", "<br/>"));
		return vo;
	}
	
	public BoardVo getboardinfo(BoardVo boardvo) {
		BoardVo vo=boardao.getboard(boardvo);
		return vo;
	}
	
	public int insert(BoardVo boardvo) {
		return boardao.insert(boardvo);
	}
	
	public int update(BoardVo boardvo) {
		return boardao.update(boardvo);
	}
	
	public int delete(int num) {
		return boardao.delete(num);
	}
	
	public int count(BoardVo boardvo) {
		return boardao.count(boardvo);
	}	
	
	public List<BoardVo> search(int currNo,String name) {
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
