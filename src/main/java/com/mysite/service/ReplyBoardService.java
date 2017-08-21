package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.PageVo;
import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.BoardVo;

@Service
public class ReplyBoardService {
	
	@Autowired
	private ReplyBoardDao replyboardao;

	
	public List<ReplyBoardVo> getlist(int currNo,PageVo pageVo) {		
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return replyboardao.getlist(endNum,startNum);
	}
	
	public PageVo getPage(int currNo) {
		int totalCount=replyboardao.gettotalcount();
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}
	
	public ReplyBoardVo getboard(ReplyBoardVo replyboardvo,int flag) {
		ReplyBoardVo vo=replyboardao.getboard(replyboardvo);
		if(flag==1) {
			replyboardao.count(replyboardvo);
		}
		vo.setContent(vo.getContent().replace("\r\n", "<br/>"));
		return vo;
	}
	
	public ReplyBoardVo getboardinfo(ReplyBoardVo replyboardvo) {
		ReplyBoardVo vo=replyboardao.getboard(replyboardvo);
		return vo;
	}
	
	public int insert(ReplyBoardVo replyboardvo) {
		return replyboardao.insert(replyboardvo);
	}
	
	public int update(ReplyBoardVo replyboardvo) {
		return replyboardao.update(replyboardvo);
	}
	
	public int delete(int num) {
		return replyboardao.delete(num);
	}
	
	public int count(ReplyBoardVo replyboardvo) {
		return replyboardao.count(replyboardvo);
	}	
	
	public List<ReplyBoardVo> search(int currNo,String name) {
		PageVo pageVo=new PageVo();
		int totalCount=replyboardao.searchcount(name);
		pageVo.setting(currNo, totalCount);
		int endNum=pageVo.getTotalCount()-pageVo.getPageNo()*(pageVo.getCurrNo()-1);
		int startNum=pageVo.getTotalCount()-pageVo.getPageNo()*pageVo.getCurrNo();		
		return replyboardao.search(name,startNum,endNum);
	}
	
	public PageVo searchPage(int currNo,String name) {
		int totalCount=replyboardao.searchcount(name);
		PageVo pageVo=new PageVo();
		pageVo.setting(currNo, totalCount);
		return pageVo;
	}

}
